package com.assignment.stableSoftware.feedingPreference;

import org.springframework.stereotype.Component;

@Component
public class FeedingPreferenceMapper {

    public FeedingPreferenceDTO toFeedingPreferenceDTO(FeedingPreference feedingPreference){
        if(feedingPreference == null)
            return null;

        FeedingPreferenceDTO feedingPreferenceDTO = new FeedingPreferenceDTO();

        feedingPreferenceDTO.setAmount(feedingPreference.getAmount());
        feedingPreferenceDTO.setFoodType(feedingPreference.getFoodType());
        feedingPreferenceDTO.setHorse(feedingPreference.getHorse());

        return feedingPreferenceDTO;
    }

    public FeedingPreference toFeedingPreference(FeedingPreferenceDTO feedingPreferenceDTO){
        if (feedingPreferenceDTO == null)
            return null;

        FeedingPreference feedingPreference = new FeedingPreference();
        feedingPreference.setFoodType(feedingPreferenceDTO.getFoodType());
        feedingPreference.setAmount(feedingPreferenceDTO.getAmount());
        feedingPreference.setHorse(feedingPreferenceDTO.getHorse());

        return feedingPreference;
    }
}
