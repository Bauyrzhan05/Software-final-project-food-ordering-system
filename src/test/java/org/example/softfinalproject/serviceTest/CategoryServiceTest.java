package org.example.softfinalproject.serviceTest;

import org.example.softfinalproject.dto.CategoryDto;
import org.example.softfinalproject.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void getAllCategoryTest(){
        List<CategoryDto> categoryDtoList = categoryService.getAll();

        Assertions.assertNotNull(categoryDtoList);
        Assertions.assertNotEquals(0, categoryDtoList.size());

        for (CategoryDto categoryDto: categoryDtoList){
            Assertions.assertNotNull(categoryDto);
            Assertions.assertNotNull(categoryDto.getId());
            Assertions.assertNotNull(categoryDto.getName());
        }
    }

    @Test
    void getCategoryTest(){
        CategoryDto categoryDtoList = categoryService.getCategory(1L);

        Assertions.assertNotNull(categoryDtoList);
        Assertions.assertNotNull(categoryDtoList.getId());
        Assertions.assertNotNull(categoryDtoList.getName());
    }

    @Test
    void addCategoryTest(){
        CategoryDto categoryDto = CategoryDto.builder().id(1L).name("name").build();

        CategoryDto addCategory = categoryService.addCategory(categoryDto);

        Assertions.assertNotNull(addCategory);
        Assertions.assertNotNull(addCategory.getId());
        Assertions.assertNotNull(addCategory.getName());

        Assertions.assertEquals(categoryDto.getId(), addCategory.getId());
        Assertions.assertEquals(categoryDto.getName(), addCategory.getName());
    }

    @Test
    void updateCategoryTest(){
        CategoryDto categoryDto = CategoryDto.builder().id(1L).name("name").build();

        CategoryDto updateCategory = categoryService.updateCategory(categoryDto.getId(), categoryDto);

        Assertions.assertNotNull(updateCategory);
        Assertions.assertNotNull(updateCategory.getId());
        Assertions.assertNotNull(updateCategory.getName());

        Assertions.assertEquals(categoryDto.getId(), updateCategory.getId());
        Assertions.assertEquals(categoryDto.getName(), updateCategory.getName());
    }

    @Test
    void deleteCategoryTest(){
        boolean deleteCategory = categoryService.deleteCategory(1L);
        Assertions.assertTrue(deleteCategory);
    }
}
