package com.assignment.stableSoftware.feedingPreference;

import com.assignment.stableSoftware.horse.Horse;
import com.assignment.stableSoftware.horse.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/feedingPreferences")
public class FeedingPreferenceController {
    private FeedingPreferenceService feedingPreferenceService;
    private FeedingPreferenceMapper feedingPreferenceMapper;
    private HorseService horseService;

    @Autowired
    public FeedingPreferenceController(FeedingPreferenceService feedingPreferenceService, FeedingPreferenceMapper feedingPreferenceMapper, HorseService horseService) {
        this.feedingPreferenceService = feedingPreferenceService;
        this.feedingPreferenceMapper = feedingPreferenceMapper;
        this.horseService = horseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedingPreferenceDTO> getOneFeedingPreference(@PathVariable("id") Long id){
        Optional<FeedingPreference> feedingPreference = feedingPreferenceService.getOneFeedingPreference(id);

        if (feedingPreference.isPresent()){
            FeedingPreferenceDTO feedingPreferenceDTO = feedingPreferenceMapper.toFeedingPreferenceDTO(feedingPreference.get());
            return ResponseEntity.ok(feedingPreferenceDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FeedingPreferenceDTO> persistFeedingPreference(@RequestBody FeedingPreferenceDTO feedingPreferenceDTO){
        Horse horse = horseService.getOneHorse(feedingPreferenceDTO.getHorseId()).orElseThrow(() -> new RuntimeException("Horse not found")); // Geänderte Zeile

        FeedingPreference feedingPreference = feedingPreferenceMapper.toFeedingPreference(feedingPreferenceDTO, horse); // Geänderte Zeile
        FeedingPreference createdFeedingPreference = feedingPreferenceService.createFeedingPreference(feedingPreference, horse.getId());
        FeedingPreferenceDTO createdFeedingPreferenceDTO = feedingPreferenceMapper.toFeedingPreferenceDTO(createdFeedingPreference);

        return ResponseEntity.ok(createdFeedingPreferenceDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedingPreferenceDTO> updateFeedingPreference(@PathVariable("id") Long id, @RequestBody FeedingPreferenceDTO feedingPreferenceDTO){
        Horse horse = horseService.getOneHorse(feedingPreferenceDTO.getHorseId()).orElseThrow(() -> new RuntimeException("Horse not found"));
        FeedingPreference feedingPreferenceDetails = feedingPreferenceMapper.toFeedingPreference(feedingPreferenceDTO, horse);
        FeedingPreference updatedFeedingPreference = feedingPreferenceService.updateFeedingPreference(id, feedingPreferenceDetails);
        FeedingPreferenceDTO updatedFeedingPreferenceDTO = feedingPreferenceMapper.toFeedingPreferenceDTO(updatedFeedingPreference);

        return ResponseEntity.ok(updatedFeedingPreferenceDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFeedingPreference(@PathVariable("id") Long id){
        feedingPreferenceService.deleteFeedingPreference(id);
        return ResponseEntity.noContent().build();
    }
}
