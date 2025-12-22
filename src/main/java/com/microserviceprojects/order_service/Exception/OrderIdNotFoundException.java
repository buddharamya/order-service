package com.microserviceprojects.order_service.Exception;

public class OrderIdNotFoundException extends RuntimeException{
    public OrderIdNotFoundException(String message) {
        super(message);
    }
}
