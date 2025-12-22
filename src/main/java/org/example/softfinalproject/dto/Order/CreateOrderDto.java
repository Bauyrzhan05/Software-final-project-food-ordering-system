package org.example.softfinalproject.dto.Order;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {
    private List<OrderItemDto> items;
}
