package com.assignment.stableSoftware.feedingTime;

import com.assignment.stableSoftware.horse.Horse;
import org.springframework.stereotype.Component;

@Component
public class FeedingTimeMapper {

    public FeedingTimeDTO toFeedingTimeDTO(FeedingTime feedingTime){
        if(feedingTime == null)
            return null;

        FeedingTimeDTO feedingTimeDTO = new FeedingTimeDTO();

        feedingTimeDTO.setLocalTime(feedingTime.getTime());
        feedingTimeDTO.setOperation(feedingTime.getOperation());
        //feedingTimeDTO.setHorse(feedingTime.getHorse());
        feedingTimeDTO.setHorseId(feedingTime.getHorse().getId());

        return feedingTimeDTO;
    }

    public FeedingTime toFeedingTime(FeedingTimeDTO feedingTimeDTO, Horse horse){
        if (feedingTimeDTO == null)
            return null;

        FeedingTime feedingTime = new FeedingTime();

        feedingTime.setTime(feedingTimeDTO.getLocalTime());
        feedingTime.setOperation(feedingTimeDTO.getOperation());
        feedingTime.setHorse(horse);

        return feedingTime;
    }
}
