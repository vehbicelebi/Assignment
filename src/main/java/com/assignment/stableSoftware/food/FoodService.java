package com.assignment.stableSoftware.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    private FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Optional<Food> getOneFood(Long id){
        return foodRepository.findById(id);
    }

    public List<Food> getAllFoods(){
        return foodRepository.findAll();
    }

    public Food createFood(Food food){
        return foodRepository.save(food);
    }

    public Food updateFood(Long id, Food foodDetails){
        Food food = foodRepository.findById(id).orElseThrow(() -> new RuntimeException("Food not found"));

        food.setHorses(foodDetails.getHorses());
        food.setName(foodDetails.getName());

        return foodRepository.save(food);
    }

    public void deleteFood(Long id){
        foodRepository.deleteById(id);
    }
}
