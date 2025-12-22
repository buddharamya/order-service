package com.microserviceprojects.order_service.Service;

import com.microserviceprojects.order_service.DTO.*;
import com.microserviceprojects.order_service.Entity.OrderEntity;
import com.microserviceprojects.order_service.Entity.OrderItem;
import com.microserviceprojects.order_service.Exception.OrderIdNotFoundException;
import com.microserviceprojects.order_service.FeignClients.ProductFeignClient;
import com.microserviceprojects.order_service.FeignClients.CartFeignClient;
import com.microserviceprojects.order_service.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartFeignClient cartFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public OrderResponseDTO placeOrder(PlaceOrderRequestDTO request) {

        List<CartItemResponseDTO> cartItems = cartFeignClient.getCartByUserId(request.getUserId());

        if(cartItems.isEmpty()) {
            throw new RuntimeException("Cart is Empty");
        }

        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList();
        List<ProductStockUpdateDTO> stockUpdates = new ArrayList<>();

        for(CartItemResponseDTO item : cartItems) {

            BigDecimal price = BigDecimal.valueOf(productFeignClient.getProductById(item.getProductId()).getPrice());
            BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(item.getQuantity()));
            total = total.add(totalPrice);

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(item.getProductId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(price);
            orderItems.add(orderItem);

            stockUpdates.add(new ProductStockUpdateDTO(item.getProductId(),item.getQuantity()));
        }

        OrderEntity order = new OrderEntity();
        order.setUserId(request.getUserId());
        order.setTotalPrice(total);
        order.setStatus("PLACED");

        for(OrderItem item : orderItems) {
            item.setOrder(order);
        }

        order.setItems(orderItems);
            OrderEntity savedOrder = orderRepository.save(order);

        productFeignClient.updateProductStock(stockUpdates);
        cartFeignClient.clearUserCart(request.getUserId());

        return mapToOrderResponse(savedOrder);
    }

    @Override
    public Boolean orderExists(Long orderId) {
        Boolean exists = orderRepository.existsById(orderId);

        if(!exists) {

            throw new OrderIdNotFoundException("Order not found with orderId: " + orderId);
        }
        return true;
    }

    private OrderResponseDTO mapToOrderResponse(OrderEntity savedOrder) {
        OrderResponseDTO response = new OrderResponseDTO();
        response.setOrderId(savedOrder.getId());
        response.setUserId(savedOrder.getUserId());
        response.setTotalPrice(savedOrder.getTotalPrice());
        response.setStatus(savedOrder.getStatus());

        var items = savedOrder.getItems().stream().map(item -> {
            OrderItemResponseDTO itemDTO = new OrderItemResponseDTO();
            itemDTO.setProductId(item.getProductId());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setPrice(item.getPrice());

            return itemDTO;
        }).collect(Collectors.toList());
        response.setItems(items);
        return response;
    }
}
