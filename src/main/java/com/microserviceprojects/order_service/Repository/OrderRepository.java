package com.microserviceprojects.order_service.Repository;

import com.microserviceprojects.order_service.Entity.OrderEntity;
import com.microserviceprojects.order_service.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
