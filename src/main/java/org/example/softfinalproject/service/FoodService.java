package org.example.softfinalproject.service;

import lombok.AllArgsConstructor;
import org.example.softfinalproject.dto.ExtraDto;
import org.example.softfinalproject.dto.FoodDto;
import org.example.softfinalproject.entity.Category;
import org.example.softfinalproject.entity.Extra;
import org.example.softfinalproject.entity.Food;
import org.example.softfinalproject.mapper.FoodMapper;
import org.example.softfinalproject.repository.CategoryRepository;
import org.example.softfinalproject.repository.ExtraRepository;
import org.example.softfinalproject.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;
    private final CategoryRepository categoryRepository;
    private final ExtraRepository extraRepository;

    public List<FoodDto> getAll(){
        return foodMapper.toDtoList(foodRepository.findAll());
    }

    public FoodDto getFood(Long id){
        return foodMapper.toDto(foodRepository.findById(id).orElseThrow());
    }

    public FoodDto addFood(FoodDto foodDto){
        Food food = foodMapper.toEntity(foodDto);

        Category category = categoryRepository.findById(foodDto.getCategoryDto().getId()).orElseThrow();
        food.setCategory(category);

        if (foodDto.getExtraDtoList() != null && !foodDto.getExtraDtoList().isEmpty()) {
            List<Long> extraIds = foodDto.getExtraDtoList()
                    .stream()
                    .map(ExtraDto::getId)
                    .toList();

            List<Extra> extras = extraRepository.findAllById(extraIds);
            food.setExtras(extras);

        } else food.setExtras(List.of());

        return foodMapper.toDto(foodRepository.save(food));
    }

    public FoodDto updateFood(Long id, FoodDto foodDto){
        Food updateFood = foodRepository.findById(id).orElseThrow();

        Category category = categoryRepository.findById(foodDto.getCategoryDto().getId()).orElseThrow();

        List<Long> extraIds = foodDto.getExtraDtoList().stream().map(ExtraDto::getId).toList();
        List<Extra> extras = extraRepository.findAllById(extraIds);

        updateFood.setName(foodDto.getName());
        updateFood.setDescription(foodDto.getDescription());
        updateFood.setPrice(foodDto.getPrice());
        updateFood.setCategory(category);
        updateFood.setExtras(extras);

        return foodMapper.toDto(foodRepository.save(updateFood));
    }

    public boolean deleteFood(Long id){
        foodRepository.deleteById(id);
        return true;
    }

}
