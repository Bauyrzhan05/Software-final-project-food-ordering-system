package org.example.softfinalproject.dto.Order;

import lombok.Data;

import java.util.List;

@Data
public class OrderItemResponseDto {
    private String foodName;
    private int quantity;
    private int price;
    private List<String> extras;
}

