package com.microserviceprojects.order_service.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponseDTO {

    private Long orderId;
    private Long userId;
    private BigDecimal totalPrice;
    private String status;
    private List<OrderItemResponseDTO> items;

}
