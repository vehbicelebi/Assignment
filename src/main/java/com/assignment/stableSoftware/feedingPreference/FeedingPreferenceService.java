package com.assignment.stableSoftware.feedingPreference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedingPreferenceService {
    private FeedingPreferenceRepository feedingPreferenceRepository;

    @Autowired
    public FeedingPreferenceService(FeedingPreferenceRepository feedingPreferenceRepository) {
        this.feedingPreferenceRepository = feedingPreferenceRepository;
    }

    public Optional<FeedingPreference> getOneFeedingPreference(Long id){
        return feedingPreferenceRepository.findById(id);
    }

    public FeedingPreference createFeedingPreference(FeedingPreference feedingPreference){
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
