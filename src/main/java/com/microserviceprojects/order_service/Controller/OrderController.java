package com.microserviceprojects.order_service.Controller;

import com.microserviceprojects.order_service.DTO.OrderResponseDTO;
import com.microserviceprojects.order_service.DTO.PlaceOrderRequestDTO;
import com.microserviceprojects.order_service.Service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
