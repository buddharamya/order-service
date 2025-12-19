package com.microserviceprojects.order_service.FeignClients;

import com.microserviceprojects.order_service.DTO.ProductResponseDTO;
import com.microserviceprojects.order_service.DTO.ProductStockUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="product-service",path="/products")
public interface ProductFeignClient {

    @GetMapping("/{productId}")
    public ProductResponseDTO getProductById(@PathVariable Long productId);

    @PutMapping("/update-stock")
    public Void updateProductStock(@RequestBody List<ProductStockUpdateDTO> request);
}
