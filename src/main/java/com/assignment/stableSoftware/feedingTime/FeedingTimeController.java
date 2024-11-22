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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feedingTime")
public class FeedingTimeController {
    private FeedingTimeService feedingTimeService;
    private HorseMapper horseMapper;
    private FeedingTimeMapper feedingTimeMapper;


    @Autowired
    public FeedingTimeController(FeedingTimeService feedingTimeService, HorseMapper horseMapper, HorseService horseService, FeedingTimeMapper feedingTimeMapper) {
        this.feedingTimeService = feedingTimeService;
        this.horseMapper = horseMapper;
        this.feedingTimeMapper = feedingTimeMapper;
    }

    @GetMapping("/eligible")
    public ResponseEntity<List<HorseDTO>> getEligibleForFeeding(@RequestParam LocalTime time){
        List<Horse> eligibleHorses = feedingTimeService.getEligibleHorses(time);
        List<HorseDTO> horseDTOS = new ArrayList<>();

        for (Horse horse : eligibleHorses) {
            horseDTOS.add(horseMapper.toHorseDTO(horse));
        }

        if (horseDTOS.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(horseDTOS);
    }

    @PostMapping
    public ResponseEntity<FeedingTimeDTO> persistFeedingTime(@RequestBody FeedingTimeDTO feedingTimeDTO){
        FeedingTime feedingTime = feedingTimeMapper.toFeedingTime(feedingTimeDTO, null);
        FeedingTime createdFeedingTime = feedingTimeService.createFeedingTime(feedingTime, feedingTimeDTO.getHorseId());
        FeedingTimeDTO createdFeedingTimeDTO = feedingTimeMapper.toFeedingTimeDTO(createdFeedingTime);

        return ResponseEntity.ok(createdFeedingTimeDTO);
    }

    @PutMapping("/releaseFood/{id}")
    public ResponseEntity<FeedingTimeDTO> persistReleaseFood(@PathVariable Long id, @RequestBody FeedingTimeDTO feedingTimeDTO){
        // FeedingTime feedingTime = feedingTimeService.getOneFeedingTime(id);
        FeedingTime feedingTimeDetails = feedingTimeMapper.toFeedingTime(feedingTimeDTO, null);
        FeedingTime updatedFeedingTime = feedingTimeService.updateFeedingTime(id, feedingTimeDetails);
        FeedingTimeDTO updatedFeedingTimeDTO = feedingTimeMapper.toFeedingTimeDTO(updatedFeedingTime);

        return ResponseEntity.ok(updatedFeedingTimeDTO);
    }

    @PutMapping("/markAsDone/{id}")
    public ResponseEntity<FeedingTimeDTO> markFeedingAsDoneMethod(@PathVariable Long id){
        FeedingTime updatedFeedingTime = feedingTimeService.markFeedingAsDone(id);
        FeedingTimeDTO updatedFeedingTimeDTO = feedingTimeMapper.toFeedingTimeDTO(updatedFeedingTime);

        return ResponseEntity.ok(updatedFeedingTimeDTO);
    }

    @GetMapping("/unfedForMoreThanXHours")
    public ResponseEntity<List<HorseDTO>> getUnfedHorsesForMoreThanXHoursMethod(@RequestParam int hours) {
        List<Horse> unfedHorses = feedingTimeService.getUnfedHorsesForMoreThanXHours(hours);
        List<HorseDTO> horseDTOS = new ArrayList<>();

        for (Horse horse : unfedHorses) {
            horseDTOS.add(horseMapper.toHorseDTO(horse));
        }

        if (horseDTOS.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(horseDTOS);
    }

    @GetMapping("/missedFeedings")
    public ResponseEntity<List<HorseDTO>> getHorsesWithMissedFeedingsMethod(@RequestParam int missedCount) {
        List<Horse> horsesWithMissedFeedings = feedingTimeService.getHorsesWithMissedFeedings(missedCount);
        List<HorseDTO> horseDTOS = new ArrayList<>();

        for (Horse horse : horsesWithMissedFeedings) {
            horseDTOS.add(horseMapper.toHorseDTO(horse));
        }

        if(horseDTOS.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(horseDTOS);
    }

    @GetMapping("/incompleteMeals")
    public ResponseEntity<List<HorseDTO>> getHorsesWithIncompleteMealsMethod() {
        List<Horse> horsesWithIncompleteMeals = feedingTimeService.getHorsesWithIncompleteMeals();
        List<HorseDTO> horseDTOS = new ArrayList<>();

        for (Horse horse : horsesWithIncompleteMeals) {
            horseDTOS.add(horseMapper.toHorseDTO(horse));
        }

        if (horseDTOS.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(horseDTOS);
    }
}
