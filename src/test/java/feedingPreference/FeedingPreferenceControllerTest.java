package feedingPreference;

import com.assignment.stableSoftware.StableSoftwareApplication;
import com.assignment.stableSoftware.feedingPreference.*;
import com.assignment.stableSoftware.feedingTime.FeedingTimeRepository;
import com.assignment.stableSoftware.stable.StableDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = StableSoftwareApplication.class)

public class FeedingPreferenceControllerTest {

    private FeedingPreferenceService feedingPreferenceService;
    private FeedingPreferenceMapper feedingPreferenceMapper;
    private FeedingTimeRepository feedingTimeRepository;
    private FeedingPreferenceController feedingPreferenceController;

    @Autowired
    public FeedingPreferenceControllerTest(FeedingPreferenceService feedingPreferenceService, FeedingPreferenceMapper feedingPreferenceMapper, FeedingTimeRepository feedingTimeRepository, FeedingPreferenceController feedingPreferenceController) {
        this.feedingPreferenceService = feedingPreferenceService;
        this.feedingPreferenceMapper = feedingPreferenceMapper;
        this.feedingTimeRepository = feedingTimeRepository;
        this.feedingPreferenceController = feedingPreferenceController;
    }

    @BeforeEach
    public void cleanUp(){
        feedingTimeRepository.deleteAll();
    }

    @Test
    public void testGetOneFeedingPreference(){
        FeedingPreference feedingPreference = new FeedingPreference();

        feedingPreference.setFoodType("meat");
        feedingPreference.setAmount(10);

        feedingPreferenceService.createFeedingPreference(feedingPreference);

        ResponseEntity<FeedingPreferenceDTO> probe = feedingPreferenceController.getOneFeedingPreference(feedingPreference.getId());
        FeedingPreferenceDTO body = probe.getBody();

        assertEquals("meat", body.getFoodType());
        assertEquals(10, body.getAmount());
    }

    @Test
    public void testPersistFeedingPreference(){
        FeedingPreferenceDTO feedingPreferenceDTO = new FeedingPreferenceDTO();
        feedingPreferenceDTO.setFoodType("fruits");
        feedingPreferenceDTO.setAmount(7);

        ResponseEntity<FeedingPreferenceDTO> probe = feedingPreferenceController.persistFeedingPreference(feedingPreferenceDTO);
        FeedingPreferenceDTO feedingPreferenceDTO1 = probe.getBody();

        assertEquals("fruits", feedingPreferenceDTO1.getFoodType());
        assertEquals(7, feedingPreferenceDTO1.getAmount());
    }

    @Test
    public void testUpdateFeedingPreference(){
        FeedingPreference feedingPreference = new FeedingPreference();
        feedingPreference.setFoodType("vegetables");
        feedingPreference.setAmount(8);

        FeedingPreference savedFeedingPreference = feedingPreferenceService.createFeedingPreference(feedingPreference);

        FeedingPreference updatedFeedingPreference = new FeedingPreference();
        updatedFeedingPreference.setId(feedingPreference.getId());
        updatedFeedingPreference.setFoodType("hay");
        updatedFeedingPreference.setAmount(2);

        FeedingPreferenceDTO feedingPreferenceDTO = feedingPreferenceMapper.toFeedingPreferenceDTO(updatedFeedingPreference);
        feedingPreferenceController.updateFeedingPreference(savedFeedingPreference.getId(), feedingPreferenceDTO);

        Optional<FeedingPreference> probe = feedingPreferenceService.getOneFeedingPreference(savedFeedingPreference.getId());

        assertEquals("hay", probe.get().getFoodType());
        assertEquals(2, probe.get().getAmount());
    }

    @Test
    public void testRemoveFeedingPreference(){
        FeedingPreference feedingPreference = new FeedingPreference();
        feedingPreference.setFoodType("chicken");
        feedingPreference.setAmount(1);

        FeedingPreference savedFeedingPreference = feedingPreferenceService.createFeedingPreference(feedingPreference);
        feedingPreferenceService.deleteFeedingPreference(feedingPreference.getId());

        Optional<FeedingPreference> deletedFeedingPreference = feedingPreferenceService.getOneFeedingPreference(savedFeedingPreference.getId());

        assertFalse(deletedFeedingPreference.isPresent());
    }
}
