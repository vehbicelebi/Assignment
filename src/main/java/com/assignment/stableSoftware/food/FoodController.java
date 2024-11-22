package com.assignment.stableSoftware.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foods")
public class FoodController {
    private FoodService foodService;
    private FoodMapper foodMapper;

    @Autowired
    public FoodController(FoodService foodService, FoodMapper foodMapper) {
        this.foodService = foodService;
        this.foodMapper = foodMapper;
    }

    @GetMapping
    public ResponseEntity<List<FoodDTO>> getFoods(){
        List<Food> foods = foodService.getAllFoods();
        List<FoodDTO> foodDTOS = foodMapper.toFoodDTOs(foods);

        return ResponseEntity.ok(foodDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodDTO> getOneFood(@PathVariable Long id){
        Optional<Food> food = foodService.getOneFood(id);

        if (food.isPresent()){
            FoodDTO foodDTO = foodMapper.toFoodDTO(food.get());
            return ResponseEntity.ok(foodDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FoodDTO> persistFood(@RequestBody FoodDTO foodDTO){
        Food food = foodMapper.toFood(foodDTO);
        Food createdFood = foodService.createFood(food);
        FoodDTO createdFoodDTO = foodMapper.toFoodDTO(createdFood);

        return ResponseEntity.ok(createdFoodDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodDTO> updateFood(@PathVariable Long id, @RequestBody FoodDTO foodDTO){
        Food foodDetails = foodMapper.toFood(foodDTO);
        Food updatedFood = foodService.updateFood(id, foodDetails);
        FoodDTO updatedFoodDTO = foodMapper.toFoodDTO(updatedFood);

        return ResponseEntity.ok(updatedFoodDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorse(@PathVariable Long id){
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }
}
