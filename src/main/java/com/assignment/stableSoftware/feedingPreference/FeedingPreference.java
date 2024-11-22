package com.assignment.stableSoftware.feedingPreference;

import com.assignment.stableSoftware.horse.Horse;
import jakarta.persistence.*;

@Entity
public class FeedingPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "food_type")
    private String foodType;
    @Column(name = "amount")
    private int amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "horse_id", referencedColumnName = "id")
    private Horse horse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
