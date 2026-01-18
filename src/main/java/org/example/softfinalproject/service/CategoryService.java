package org.example.softfinalproject.service;

import lombok.AllArgsConstructor;
import org.example.softfinalproject.dto.CategoryDto;
import org.example.softfinalproject.entity.Category;
import org.example.softfinalproject.mapper.CategoryMapper;
import org.example.softfinalproject.repository.CategoryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Cacheable(value = "categories", key = "'all_categories'")
    public List<CategoryDto> getAll(){
        return categoryMapper.toDtoList(categoryRepository.findAll());
    }

    @Cacheable(value = "category", key = "#id")
    public CategoryDto getCategory(Long id){
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow());
    }

    public CategoryDto addCategory(CategoryDto categoryDto){
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(categoryDto)));
    }

    @CacheEvict(value = "category", key = "#id")
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto){
        Category update = categoryRepository.findById(id).orElseThrow();

        update.setName(categoryDto.getName());

        return categoryMapper.toDto(categoryRepository.save(update));
    }

    @CacheEvict(value = "category", key = "#id")
    public boolean deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return true;
    }

}
