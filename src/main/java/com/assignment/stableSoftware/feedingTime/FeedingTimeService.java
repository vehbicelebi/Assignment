package com.assignment.stableSoftware.feedingTime;

import com.assignment.stableSoftware.horse.Horse;
import com.assignment.stableSoftware.horse.HorseRepository;
import com.assignment.stableSoftware.operation.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

@Service
public class FeedingTimeService {
    private FeedingTimeRepository feedingTimeRepository;
    private HorseRepository horseRepository;

    @Autowired
    public FeedingTimeService(FeedingTimeRepository feedingTimeRepository, HorseRepository horseRepository) {
        this.feedingTimeRepository = feedingTimeRepository;
        this.horseRepository = horseRepository;
    }

    public FeedingTime createFeedingTime(FeedingTime feedingTime, Long horseId){
        Horse horse = horseRepository.findById(horseId).orElseThrow(() -> new RuntimeException("Horse not found"));
        feedingTime.setHorse(horse);
        return feedingTimeRepository.save(feedingTime);
    }

    public List<Horse> getEligibleHorses(LocalTime time){
        List<Horse> eligibleHorses = new ArrayList<>();
        List<FeedingTime> feedingTimes = feedingTimeRepository.findAll();

        for (FeedingTime ft : feedingTimes){
            if (time.equals(ft.getTime())){
                eligibleHorses.add(ft.getHorse());
            }
        }
        return eligibleHorses;
    }

    public FeedingTime updateFeedingTime(Long id, FeedingTime feedingTimeDetails){
        FeedingTime feedingTime = feedingTimeRepository.findById(id).orElseThrow(() -> new RuntimeException("Feeding time not found"));
        feedingTime.setOperation(feedingTimeDetails.getOperation());
        feedingTime.setTime(feedingTimeDetails.getTime());
        feedingTime.setHorse(feedingTimeDetails.getHorse());
        feedingTime.setId(feedingTimeDetails.getId());

        return feedingTimeRepository.save(feedingTime);
    }

    public FeedingTime markFeedingAsDone(Long id){
        FeedingTime feedingTime = feedingTimeRepository.findById(id).orElseThrow(() -> new RuntimeException("Feeding time not found"));
        feedingTime.setOperation(Operation.DONE);

        return feedingTimeRepository.save(feedingTime);
    }

    public List<Horse> getUnfedHorsesForMoreThanXHours(int hours){
        List<Horse> unfedHorses = new ArrayList<>();
        List<FeedingTime> feedingTimes = feedingTimeRepository.findAll();
        LocalTime currentTime = LocalTime.now();

        for (FeedingTime ft : feedingTimes){
            if (ft.getOperation() != Operation.DONE && Duration.between(ft.getTime(), currentTime).toHours() > hours){
                unfedHorses.add(ft.getHorse());
            }
        }

        return unfedHorses;
    }

    public List<Horse> getHorsesWithMissedFeedings(int missedCount) {
        List<Horse> horsesWithMissedFeedings = new ArrayList<>();
        List<FeedingTime> feedingTimes = feedingTimeRepository.findAll();
        Map<Horse, Integer> missedFeedignMap = new HashMap<>();

        for (FeedingTime ft : feedingTimes) {
            if (ft.getOperation() != Operation.DONE) {
                missedFeedignMap.put(ft.getHorse(), missedFeedignMap.getOrDefault(ft.getHorse(), 0) + 1);
            } else {
                missedFeedignMap.put(ft.getHorse(), 0);
            }
        }

        for (Map.Entry<Horse, Integer> entry : missedFeedignMap.entrySet()) {
            if (entry.getValue() >= missedCount) {
                horsesWithMissedFeedings.add(entry.getKey());
            }
        }

        return horsesWithMissedFeedings;
    }

    public List<Horse> getHorsesWithIncompleteMeals() {
        List<Horse> horsesWithIncompleteMeals = new ArrayList<>();
        List<FeedingTime> feedingTimes = feedingTimeRepository.findAll();

        for (FeedingTime ft : feedingTimes) {
            if (ft.getOperation() != Operation.DONE) {
                horsesWithIncompleteMeals.add(ft.getHorse());
            }
        }

        return horsesWithIncompleteMeals;
    }
}