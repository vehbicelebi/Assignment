package com.assignment.stableSoftware.food;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FoodMapper {
    public FoodDTO toFoodDTO(Food food){
        if (food == null)
            return null;

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName(food.getName());

        return foodDTO;
    }

    public Food toFood(FoodDTO foodDTO){
        if(foodDTO == null)
            return null;

        Food food = new Food();
        food.setName(foodDTO.getName());

        return food;
    }

    public List<FoodDTO> toFoodDTOs(List<Food> foods){
        if (foods == null)
            return null;

        List<FoodDTO> foodDTOS = new ArrayList<>();

        for (Food food : foods){
            foodDTOS.add(toFoodDTO(food));
        }

        return foodDTOS;
    }
}
