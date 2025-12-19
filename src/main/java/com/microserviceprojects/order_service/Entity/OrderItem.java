package com.microserviceprojects.order_service.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="order_items")
@Setter
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Integer Quantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="order_id")
    private OrderEntity order;
}
