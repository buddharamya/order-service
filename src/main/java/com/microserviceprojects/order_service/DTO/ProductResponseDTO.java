package com.microserviceprojects.order_service.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
