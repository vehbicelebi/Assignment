package com.assignment.stableSoftware.feedingTime;

import org.springframework.stereotype.Component;

@Component
public class FeedingTimeMapper {

    public FeedingTimeDTO toFeedingTimeDTO(FeedingTime feedingTime){
        if(feedingTime == null)
            return null;

        FeedingTimeDTO feedingTimeDTO = new FeedingTimeDTO();

        feedingTimeDTO.setLocalTime(feedingTime.getTime());
        feedingTimeDTO.setOperation(feedingTime.getOperation());

        return feedingTimeDTO;
    }

    public FeedingTime toFeedingTime(FeedingTimeDTO feedingTimeDTO){
        if (feedingTimeDTO == null)
            return null;

        FeedingTime feedingTime = new FeedingTime();

        feedingTime.setTime(feedingTimeDTO.getLocalTime());
        feedingTime.setOperation(feedingTimeDTO.getOperation());

        return feedingTime;
    }
}
