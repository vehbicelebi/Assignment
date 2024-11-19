package com.assignment.stableSoftware.feedingTime;

import com.assignment.stableSoftware.horse.Horse;
import com.assignment.stableSoftware.operation.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class FeedingTimeService {
    private FeedingTimeRepository feedingTimeRepository;
    private Operation operation;

    @Autowired
    public FeedingTimeService(FeedingTimeRepository feedingTimeRepository) {
        this.feedingTimeRepository = feedingTimeRepository;
    }

    public boolean eligibleForFeeding(Horse horse, LocalTime time){
        List<FeedingTime> feedingTimes = horse.getFeedingTimes();

        for (FeedingTime ft : feedingTimes){
            if(time == ft.getTime())
                return true;
        }

        return false;
    }

    public Operation releaseFood(){
        return Operation.DONE;
    }

    /*
    public FeedingTime getOneFeedingTime(Long id){
        return feedingTimeRepository.findById(id);
    }
   */
    public FeedingTime updateFeedingTime(Long id, FeedingTime feedingTimeDetails){
        FeedingTime feedingTime = feedingTimeRepository.findById(id).orElseThrow(() -> new RuntimeException("Feeding time not found"));
        feedingTime.setOperation(feedingTimeDetails.getOperation());
        feedingTime.setTime(feedingTimeDetails.getTime());
        feedingTime.setHorse(feedingTimeDetails.getHorse());
        feedingTime.setId(feedingTimeDetails.getId());

        return feedingTimeRepository.save(feedingTime);
    }

    public boolean checkUnfedHorses(LocalTime time){
        List<FeedingTime> feedingTimes = feedingTimeRepository.findAll();

        for (FeedingTime ft : feedingTimes){
            if (time.getHour() > ft.getTime().getHour()){
                ft.setMissedFeedingRange(ft.getMissedFeedingRange() + 1);
                return true;
            }
        }
        return false;
    }

}
