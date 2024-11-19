package com.assignment.stableSoftware.feedingPreference;

public class FeedingPreferenceDTO {
    private String foodType;
    private int amount;

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
}
