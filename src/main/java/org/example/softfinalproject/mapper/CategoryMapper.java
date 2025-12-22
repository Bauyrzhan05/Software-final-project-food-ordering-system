package org.example.softfinalproject.mapper;

import org.example.softfinalproject.dto.CategoryDto;
import org.example.softfinalproject.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);

    List<CategoryDto> toDtoList(List<Category> categories);

}
