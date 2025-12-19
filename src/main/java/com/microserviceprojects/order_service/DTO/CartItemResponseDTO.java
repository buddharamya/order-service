package com.microserviceprojects.order_service.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemResponseDTO {

    private Long userId;
    private Long productId;
    private Integer quantity;
}
