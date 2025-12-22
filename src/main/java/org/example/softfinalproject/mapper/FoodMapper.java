package org.example.softfinalproject.mapper;

import org.example.softfinalproject.dto.FoodDto;
import org.example.softfinalproject.entity.Food;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, ExtraMapper.class})
public interface FoodMapper {

    @Mapping(source = "category", target = "categoryDto")
    @Mapping(source = "extras", target = "extraDtoList")
    FoodDto toDto(Food food);

    @Mapping(source = "categoryDto", target = "category")
    @Mapping(source = "extraDtoList", target = "extras")
    Food toEntity(FoodDto dto);

    List<FoodDto> toDtoList(List<Food> foods);
}
