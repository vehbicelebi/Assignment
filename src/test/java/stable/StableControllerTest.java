package stable;

import com.assignment.stableSoftware.StableSoftwareApplication;
import com.assignment.stableSoftware.stable.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = StableSoftwareApplication.class)

public class StableControllerTest {

    private StableRepository stableRepository;
    private StableMapper stableMapper;
    private StableService stableService;
    private StableController stableController;

    @Autowired
    public StableControllerTest(StableRepository stableRepository, StableMapper stableMapper, StableService stableService, StableController stableController) {
        this.stableRepository = stableRepository;
        this.stableMapper = stableMapper;
        this.stableService = stableService;
        this.stableController = stableController;
    }

    @BeforeEach
    public void cleanUp(){
        stableRepository.deleteAll();
    }

    @Test
    public void testGetOneStable(){
        Stable stable = new Stable();
        stable.setLocation("test location");
        stable.setName("Stable 12");
        stableService.createStable(stable);

        ResponseEntity<StableDTO> probe = stableController.getOneStable(stable.getId());
        StableDTO body = probe.getBody();

        assertEquals("test location", body.getLocation());
        assertEquals("Stable 12", body.getName());
    }

    @Test
    public void testGetStables(){

        Stable stable = new Stable();
        stable.setLocation("test location2");
        stable.setName("Stable 13");
        stableService.createStable(stable);

        Stable stable2 = new Stable();
        stable2.setLocation("test location3");
        stable2.setName("Stable 14");
        stableService.createStable(stable2);

        ResponseEntity<List<StableDTO>> probe = stableController.getStables();
        List<StableDTO> body = probe.getBody();

        for (StableDTO stableDTO : body) {
            assertEquals("test location2", body.getFirst().getLocation());
            assertEquals("Stable 13", body.getFirst().getName());

            assertEquals("test location3", body.getLast().getLocation());
            assertEquals("Stable 14", body.getLast().getName());
        }
    }

    @Test
    public void testPersistStable(){
        StableDTO stableDTO = new StableDTO();
        stableDTO.setLocation("test location 100");
        stableDTO.setName("test name for stable");

        ResponseEntity<StableDTO> probe = stableController.persistStable(stableDTO);
        StableDTO stableDTO1 = probe.getBody();

        assertEquals("test location 100", stableDTO1.getLocation());
        assertEquals("test name for stable", stableDTO1.getName());
    }

    @Test
    public void testUpdateStable(){
        Stable stable = new Stable();
        stable.setLocation("test location 11");
        stable.setName("test name");

        Stable savedStable = stableService.createStable(stable);

        Stable updatedStable = new Stable();
        updatedStable.setId(stable.getId());
        updatedStable.setLocation("test updated location");
        updatedStable.setName("updated name");

        StableDTO stableDTO = stableMapper.toStableDTO(updatedStable);
        stableController.updateStable(savedStable.getId(), stableDTO);

        Optional<Stable> probe = stableService.getOneStable(savedStable.getId());

        assertEquals("test updated location", probe.get().getLocation());
        assertEquals("updated name", probe.get().getName());
    }

    @Test
    public void testDeleteStable(){
        Stable stable = new Stable();
        stable.setLocation("irgendwas");
        stable.setName("ein name");

        Stable savedStable = stableService.createStable(stable);
        stableService.deleteStable(stable.getId());

        Optional<Stable> deletedStable = stableService.getOneStable(savedStable.getId());

        assertFalse(deletedStable.isPresent());
    }
}
