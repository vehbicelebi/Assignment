package com.assignment.stableSoftware.feedingPreference;

import com.assignment.stableSoftware.horse.Horse;
import com.assignment.stableSoftware.horse.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedingPreferenceService {
    private FeedingPreferenceRepository feedingPreferenceRepository;
    private HorseRepository horseRepository;

    @Autowired
    public FeedingPreferenceService(FeedingPreferenceRepository feedingPreferenceRepository, HorseRepository horseRepository) {
        this.feedingPreferenceRepository = feedingPreferenceRepository;
        this.horseRepository = horseRepository;
    }

    public Optional<FeedingPreference> getOneFeedingPreference(Long id){
        return feedingPreferenceRepository.findById(id);
    }

    public FeedingPreference createFeedingPreference(FeedingPreference feedingPreference){

        Horse horse = horseRepository.findById(feedingPreference.getHorse().getId()).orElseThrow(() -> new RuntimeException("Horse not found"));
        feedingPreference.setHorse(horse);
        return feedingPreferenceRepository.save(feedingPreference);
    }

    public FeedingPreference updateFeedingPreference(Long id, FeedingPreference feedingPreferenceDetails){
        FeedingPreference feedingPreference = feedingPreferenceRepository.findById(id).orElseThrow(() -> new RuntimeException("FeedingPreference not found"));

        feedingPreference.setFoodType(feedingPreferenceDetails.getFoodType());
        feedingPreference.setAmount(feedingPreferenceDetails.getAmount());
        feedingPreference.setHorse(feedingPreferenceDetails.getHorse());

        return feedingPreferenceRepository.save(feedingPreference);
    }

    public void deleteFeedingPreference(Long id){
        feedingPreferenceRepository.deleteById(id);
    }
}
