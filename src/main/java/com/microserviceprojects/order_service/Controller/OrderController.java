package com.microserviceprojects.order_service.Controller;

import com.microserviceprojects.order_service.DTO.OrderResponseDTO;
import com.microserviceprojects.order_service.DTO.PlaceOrderRequestDTO;
import com.microserviceprojects.order_service.Service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderResponseDTO placeOrder(@RequestBody PlaceOrderRequestDTO request) {
        log.info("OrderController placeOrder");
        OrderResponseDTO response = orderService.placeOrder(request);
        return response;
    }

    @GetMapping("/exists/{orderId}")
    public Boolean orderExists(@PathVariable Long orderId) {
        log.info("OrderController orderExists");
        return orderService.orderExists(orderId);
    }
}
