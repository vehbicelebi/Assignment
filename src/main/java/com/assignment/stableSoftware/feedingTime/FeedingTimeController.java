package com.assignment.stableSoftware.feedingTime;

import com.assignment.stableSoftware.horse.Horse;
import com.assignment.stableSoftware.horse.HorseDTO;
import com.assignment.stableSoftware.horse.HorseMapper;
import com.assignment.stableSoftware.horse.HorseService;
import com.assignment.stableSoftware.operation.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feedingTime")
public class FeedingTimeController {
    private FeedingTimeService feedingTimeService;
    private HorseMapper horseMapper;
    private HorseService horseService;
    private FeedingTimeMapper feedingTimeMapper;


    @Autowired
    public FeedingTimeController(FeedingTimeService feedingTimeService, HorseMapper horseMapper, HorseService horseService, FeedingTimeMapper feedingTimeMapper) {
        this.feedingTimeService = feedingTimeService;
        this.horseMapper = horseMapper;
        this.horseService = horseService;
        this.feedingTimeMapper = feedingTimeMapper;
    }

    @GetMapping("/eligible")
    public ResponseEntity<List<Horse>> getEligibleForFeeding(@RequestParam LocalTime time){
        List<Horse> horses = horseService.getAllHorses();
        List<Horse> result = null;

        for (Horse horse : horses){
            if (feedingTimeService.eligibleForFeeding(horse, time)) {
                result.add(horse);
            }
        }

        if(result.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(result);
    }

    @PutMapping("/releaseFood/{id}")
    public ResponseEntity<FeedingTimeDTO> persistReleaseFood(@PathVariable Long id, @RequestBody FeedingTimeDTO feedingTimeDTO){
        // FeedingTime feedingTime = feedingTimeService.getOneFeedingTime(id);
        FeedingTime feedingTimeDetails = feedingTimeMapper.toFeedingTime(feedingTimeDTO);
        FeedingTime updatedFeedingTime = feedingTimeService.updateFeedingTime(id, feedingTimeDetails);
        FeedingTimeDTO updatedFeedingTimeDTO = feedingTimeMapper.toFeedingTimeDTO(updatedFeedingTime);

        return ResponseEntity.ok(updatedFeedingTimeDTO);
    }
}
