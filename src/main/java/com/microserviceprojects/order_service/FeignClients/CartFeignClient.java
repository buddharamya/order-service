package com.microserviceprojects.order_service.FeignClients;

import com.microserviceprojects.order_service.DTO.CartItemResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="cart-service",path="cart")
public interface CartFeignClient {

    @GetMapping("/{userId}")
    public List<CartItemResponseDTO> getCartByUserId(@PathVariable Long userId);

    @DeleteMapping("/clear/{userId}")
    public Void clearUserCart(@PathVariable Long userId);


}
