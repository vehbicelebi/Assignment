package com.assignment.stableSoftware.horse;

import com.assignment.stableSoftware.feedingTime.FeedingTime;
import com.assignment.stableSoftware.food.Food;
import com.assignment.stableSoftware.owner.Owner;
import com.assignment.stableSoftware.stable.Stable;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Horse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "guid")
    private String guid;
    @Column(name = "official_name")
    private String officialName;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "breed")
    private String breed;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "stable_id")
    private Stable stable;

    @ManyToMany
    @JoinTable(
            name = "horse_food",
            joinColumns = @JoinColumn(name = "horse_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private List<Food> foods;

    @OneToMany(mappedBy = "horse", cascade = CascadeType.ALL) // optional: orphanRemoval = true
    private List<FeedingTime> feedingTimes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Stable getStable() {
        return stable;
    }

    public void setStable(Stable stable) {
        this.stable = stable;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<FeedingTime> getFeedingTimes() {
        return feedingTimes;
    }

    public void setFeedingTimes(List<FeedingTime> feedingTimes) {
        this.feedingTimes = feedingTimes;
    }
}
