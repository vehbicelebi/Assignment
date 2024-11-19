package com.assignment.stableSoftware.feedingPreference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/feedingPreferences")
public class FeedingPreferenceController {
    private FeedingPreferenceService feedingPreferenceService;
    private FeedingPreferenceMapper feedingPreferenceMapper;

    @Autowired
    public FeedingPreferenceController(FeedingPreferenceService feedingPreferenceService, FeedingPreferenceMapper feedingPreferenceMapper) {
        this.feedingPreferenceService = feedingPreferenceService;
        this.feedingPreferenceMapper = feedingPreferenceMapper;
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
        FeedingPreference feedingPreference = feedingPreferenceMapper.toFeedingPreference(feedingPreferenceDTO);
        FeedingPreference createdFeedingPreference = feedingPreferenceService.createFeedingPreference(feedingPreference);
        FeedingPreferenceDTO createdFeedingPreferenceDTO = feedingPreferenceMapper.toFeedingPreferenceDTO(createdFeedingPreference);

        return ResponseEntity.ok(createdFeedingPreferenceDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedingPreferenceDTO> updateFeedingPreference(@PathVariable("id") Long id, @RequestBody FeedingPreferenceDTO feedingPreferenceDTO){
        FeedingPreference feedingPreferenceDetails = feedingPreferenceMapper.toFeedingPreference(feedingPreferenceDTO);
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
