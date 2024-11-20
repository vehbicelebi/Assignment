package feedingTime;

import com.assignment.stableSoftware.StableSoftwareApplication;
import com.assignment.stableSoftware.feedingPreference.FeedingPreferenceController;
import com.assignment.stableSoftware.feedingTime.*;
import com.assignment.stableSoftware.horse.Horse;
import com.assignment.stableSoftware.horse.HorseDTO;
import com.assignment.stableSoftware.horse.HorseMapper;
import com.assignment.stableSoftware.operation.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = StableSoftwareApplication.class)
public class FeedingTimeControllerTest {
    @Mock
    private FeedingTimeService feedingTimeService;

    @Mock
    private HorseMapper horseMapper;

    @Mock
    private FeedingTimeMapper feedingTimeMapper;

    @InjectMocks
    private FeedingTimeController feedingTimeController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEligibleForFeeding(){
        LocalTime time = LocalTime.of(10,30);
        List<Horse> eligibleHorses = new ArrayList<>();
        Horse horse = new Horse();
        eligibleHorses.add(horse);

        HorseDTO horseDTO = new HorseDTO();

        when(feedingTimeService.getEligibleHorses(time)).thenReturn(eligibleHorses);
        when(horseMapper.toHorseDTO(horse)).thenReturn(horseDTO);

        ResponseEntity<List<HorseDTO>> response = feedingTimeController.getEligibleForFeeding(time);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(horseDTO, response.getBody().get(0));

        verify(feedingTimeService, times(1)).getEligibleHorses(time);
        verify(horseMapper, times(1)).toHorseDTO(horse);
    }

    @Test
    public void testMarkFeedingAsDone(){
        FeedingTime feedingTime = new FeedingTime();
        feedingTime.setId(1L);
        feedingTime.setOperation(Operation.DONE);

        FeedingTimeDTO feedingTimeDTO = new FeedingTimeDTO();
        feedingTimeDTO.setOperation(Operation.DONE);

        when(feedingTimeService.markFeedingAsDone(feedingTime.getId())).thenReturn(feedingTime);
        when(feedingTimeMapper.toFeedingTimeDTO(feedingTime)).thenReturn(feedingTimeDTO);

        ResponseEntity<FeedingTimeDTO> response = feedingTimeController.markFeedingAsDoneMethod(feedingTime.getId());

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(feedingTimeDTO, response.getBody());

        verify(feedingTimeService, times(1)).markFeedingAsDone(feedingTime.getId());
        verify(feedingTimeMapper, times(1)).toFeedingTimeDTO(feedingTime);
    }

    @Test
    public void testGetUnfedHorsesForMoreThanXHours() {
        int hours = 5;
        List<Horse> unfedHorses = new ArrayList<>();
        Horse horse = new Horse();
        unfedHorses.add(horse);

        HorseDTO horseDTO = new HorseDTO();

        when(feedingTimeService.getUnfedHorsesForMoreThanXHours(hours)).thenReturn(unfedHorses);
        when(horseMapper.toHorseDTO(horse)).thenReturn(horseDTO);

        ResponseEntity<List<HorseDTO>> response = feedingTimeController.getUnfedHorsesForMoreThanXHoursMethod(hours);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(horseDTO, response.getBody().get(0));

        verify(feedingTimeService, times(1)).getUnfedHorsesForMoreThanXHours(hours);
        verify(horseMapper, times(1)).toHorseDTO(horse);
    }

    @Test
    public void testGetHorsesWithMissedFeedings() {
        int missedCount = 2;
        List<Horse> horsesWithMissedFeedings = new ArrayList<>();
        Horse horse = new Horse();
        horsesWithMissedFeedings.add(horse);

        HorseDTO horseDTO = new HorseDTO();

        when(feedingTimeService.getHorsesWithMissedFeedings(missedCount)).thenReturn(horsesWithMissedFeedings);
        when(horseMapper.toHorseDTO(horse)).thenReturn(horseDTO);

        ResponseEntity<List<HorseDTO>> response = feedingTimeController.getHorsesWithMissedFeedingsMethod(missedCount);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(horseDTO, response.getBody().get(0));

        verify(feedingTimeService, times(1)).getHorsesWithMissedFeedings(missedCount);
        verify(horseMapper, times(1)).toHorseDTO(horse);
    }

    @Test
    public void testGetHorsesWithIncompleteMeals() {
        List<Horse> horsesWithIncompleteMeals = new ArrayList<>();
        Horse horse = new Horse();
        horsesWithIncompleteMeals.add(horse);

        HorseDTO horseDTO = new HorseDTO();

        when(feedingTimeService.getHorsesWithIncompleteMeals()).thenReturn(horsesWithIncompleteMeals);
        when(horseMapper.toHorseDTO(horse)).thenReturn(horseDTO);

        ResponseEntity<List<HorseDTO>> response = feedingTimeController.getHorsesWithIncompleteMealsMethod();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(horseDTO, response.getBody().get(0));

        verify(feedingTimeService, times(1)).getHorsesWithIncompleteMeals();
        verify(horseMapper, times(1)).toHorseDTO(horse);
    }
}
