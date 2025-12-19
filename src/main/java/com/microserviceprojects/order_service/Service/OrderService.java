package com.microserviceprojects.order_service.Service;

import com.microserviceprojects.order_service.DTO.OrderResponseDTO;
import com.microserviceprojects.order_service.DTO.PlaceOrderRequestDTO;

public interface OrderService {

    OrderResponseDTO placeOrder(PlaceOrderRequestDTO request);
}
