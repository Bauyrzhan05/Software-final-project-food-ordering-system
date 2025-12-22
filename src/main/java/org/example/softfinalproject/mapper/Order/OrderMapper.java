package org.example.softfinalproject.mapper.Order;

import org.example.softfinalproject.dto.Order.OrderResponseDto;
import org.example.softfinalproject.entity.Order.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    OrderResponseDto toDto(Order order);

    List<OrderResponseDto> toDtoList(List<Order> orders);
}

