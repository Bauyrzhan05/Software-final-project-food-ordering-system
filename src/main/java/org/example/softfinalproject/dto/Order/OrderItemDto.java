package org.example.softfinalproject.dto.Order;

import lombok.Data;

import java.util.List;

@Data
public class OrderItemDto {
    private Long foodId;
    private int quantity;
    private List<Long> extraIds;
}

