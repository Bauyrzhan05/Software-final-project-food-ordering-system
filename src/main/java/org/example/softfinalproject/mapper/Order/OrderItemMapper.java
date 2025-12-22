package org.example.softfinalproject.mapper.Order;

import org.example.softfinalproject.dto.Order.OrderItemResponseDto;
import org.example.softfinalproject.entity.Extra;
import org.example.softfinalproject.entity.Order.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "food.name", target = "foodName")
    @Mapping(source = "food.price", target = "price")
    @Mapping(source = "extras", target = "extras")
    OrderItemResponseDto toDto(OrderItem orderItem);

    List<OrderItemResponseDto> toDtoList(List<OrderItem> items);

    default List<String> map(List<Extra> extras) {
        if (extras == null) return List.of();
        return extras.stream()
                .map(Extra::getName)
                .toList();
    }
}

