package org.example.softfinalproject.dto.Order;

import lombok.Data;
import org.example.softfinalproject.entity.Order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long id;
    private OrderStatus status;
    private int totalPrice;
    private LocalDateTime createdAt;
    private List<OrderItemResponseDto> items;
}

