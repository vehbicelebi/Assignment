package com.assignment.stableSoftware.feedingPreference;

import com.assignment.stableSoftware.horse.Horse;
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
        feedingPreferenceDTO.setHorseId(feedingPreference.getHorse().getId());


        return feedingPreferenceDTO;
    }

    public FeedingPreference toFeedingPreference(FeedingPreferenceDTO feedingPreferenceDTO, Horse horse){
        if (feedingPreferenceDTO == null)
            return null;

        FeedingPreference feedingPreference = new FeedingPreference();
        feedingPreference.setFoodType(feedingPreferenceDTO.getFoodType());
        feedingPreference.setAmount(feedingPreferenceDTO.getAmount());
        feedingPreference.setHorse(feedingPreferenceDTO.getHorse());
        feedingPreference.setHorse(horse);
        return feedingPreference;
    }
}
