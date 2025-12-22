package org.example.softfinalproject.serviceTest;

import org.example.softfinalproject.dto.CategoryDto;
import org.example.softfinalproject.dto.FoodDto;
import org.example.softfinalproject.service.FoodService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FoodServiceTest {

    @Autowired
    private FoodService foodService;

    @Test
    void getAllFoodsTest(){
        List<FoodDto> foodDtoList = foodService.getAll();

        Assertions.assertNotNull(foodService);
        Assertions.assertNotEquals(0, foodDtoList.size());

        for (FoodDto foodDto: foodDtoList){
            Assertions.assertNotNull(foodDto);
            Assertions.assertNotNull(foodDto.getId());
            Assertions.assertNotNull(foodDto.getName());
            Assertions.assertNotNull(foodDto.getDescription());
            Assertions.assertNotNull(foodDto.getPrice());
            Assertions.assertNotNull(foodDto.getCategoryDto());
            Assertions.assertNotNull(foodDto.getExtraDtoList());
        }
    }

    @Test
    void getFoodTest(){
        FoodDto foodDto = foodService.getFood(1L);

        Assertions.assertNotNull(foodDto);
        Assertions.assertNotNull(foodDto.getId());
        Assertions.assertNotNull(foodDto.getName());
        Assertions.assertNotNull(foodDto.getDescription());
        Assertions.assertNotNull(foodDto.getPrice());
        Assertions.assertNotNull(foodDto.getCategoryDto());
        Assertions.assertNotNull(foodDto.getExtraDtoList());
    }

    @Test
    void addFoodTest(){
        FoodDto foodDto = FoodDto.builder()
                .id(1L)
                .name("name")
                .description("desc")
                .price(1000)
                .categoryDto(new CategoryDto())
                .extraDtoList(List.of())
                .build();

        FoodDto addFood = foodService.addFood(foodDto);

        Assertions.assertNotNull(addFood);
        Assertions.assertNotNull(addFood.getId());
        Assertions.assertNotNull(addFood.getName());
        Assertions.assertNotNull(addFood.getDescription());
        Assertions.assertNotNull(addFood.getPrice());
        Assertions.assertNotNull(addFood.getCategoryDto());
        Assertions.assertNotNull(addFood.getExtraDtoList());

        Assertions.assertEquals(foodDto.getId(), addFood.getId());
        Assertions.assertEquals(foodDto.getName(), addFood.getName());
        Assertions.assertEquals(foodDto.getDescription(), addFood.getDescription());
        Assertions.assertEquals(foodDto.getPrice(), addFood.getPrice());
        Assertions.assertEquals(foodDto.getCategoryDto(), addFood.getCategoryDto());
        Assertions.assertEquals(foodDto.getExtraDtoList(), addFood.getExtraDtoList());
    }

    @Test
    void updateFoodTest(){
        FoodDto foodDto = FoodDto.builder()
                .id(1L)
                .name("name")
                .description("desc")
                .price(1000)
                .categoryDto(new CategoryDto())
                .extraDtoList(List.of())
                .build();

        FoodDto updateFood = foodService.updateFood(foodDto.getId(), foodDto);

        Assertions.assertNotNull(updateFood);
        Assertions.assertNotNull(updateFood.getId());
        Assertions.assertNotNull(updateFood.getName());
        Assertions.assertNotNull(updateFood.getDescription());
        Assertions.assertNotNull(updateFood.getPrice());
        Assertions.assertNotNull(updateFood.getCategoryDto());
        Assertions.assertNotNull(updateFood.getExtraDtoList());

        Assertions.assertEquals(foodDto.getId(), updateFood.getId());
        Assertions.assertEquals(foodDto.getName(), updateFood.getName());
        Assertions.assertEquals(foodDto.getDescription(), updateFood.getDescription());
        Assertions.assertEquals(foodDto.getPrice(), updateFood.getPrice());
        Assertions.assertEquals(foodDto.getCategoryDto(), updateFood.getCategoryDto());
        Assertions.assertEquals(foodDto.getExtraDtoList(), updateFood.getExtraDtoList());
    }

    @Test
    void deleteFoodTest(){
        boolean deleteFood = foodService.deleteFood(1L);
        Assertions.assertTrue(deleteFood);
    }

}
