package com.assignment.stableSoftware.feedingPreference;

import com.assignment.stableSoftware.horse.Horse;

public class FeedingPreferenceDTO {
    private String foodType;
    private int amount;
    private Horse horse;

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }
}
