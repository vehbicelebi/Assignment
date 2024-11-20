package horse;

import com.assignment.stableSoftware.StableSoftwareApplication;
import com.assignment.stableSoftware.horse.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = StableSoftwareApplication.class)
public class HorseControllerTest {

    private HorseService horseService;
    private HorseController horseController;
    private HorseRepository horseRepository;
    private HorseMapper horseMapper;

    @Autowired
    public HorseControllerTest(HorseService horseService, HorseController horseController, HorseRepository horseRepository, HorseMapper horseMapper) {
        this.horseService = horseService;
        this.horseController = horseController;
        this.horseRepository = horseRepository;
        this.horseMapper = horseMapper;
    }

    @BeforeEach
    public void cleanUp(){
        horseRepository.deleteAll();
    }


    @Test
    public void testGetHorses(){
        Horse horse = new Horse();
        horse.setBreed("araber");
        horse.setGuid("63bac76a-d892-436b-abcf-e6e3ea733943");
        horse.setNickName("abby");
        horse.setOfficialName("abigail");
        horseService.createHorse(horse);

        Horse horse2 = new Horse();
        horse2.setBreed("borana");
        horse2.setGuid("6dd81170-6053-4dd6-8faf-9207c501c5a4");
        horse2.setNickName("frido");
        horse2.setOfficialName("fridolin");
        horseService.createHorse(horse2);

        ResponseEntity<List<HorseDTO>> probe = horseController.getHorses();
        List<HorseDTO> body = probe.getBody();

        for (HorseDTO horseDTO : body){
            // horse 1
            assertEquals("araber", body.getFirst().getBreed());
            assertEquals("63bac76a-d892-436b-abcf-e6e3ea733943", body.getFirst().getGuid());
            assertEquals("abby", body.getFirst().getNickName());
            assertEquals("abigail", body.getFirst().getOfficialName());

            // horse 2
            assertEquals("borana", body.getLast().getBreed());
            assertEquals("6dd81170-6053-4dd6-8faf-9207c501c5a4", body.getLast().getGuid());
            assertEquals("frido", body.getLast().getNickName());
            assertEquals("fridolin", body.getLast().getOfficialName());
        }
    }

    @Test
    public void testGetOneHorse(){
        Horse horse = new Horse();

        horse.setBreed("campolina");
        horse.setGuid("a5494867-d73a-4e8a-8620-0a3959710e9d");
        horse.setNickName("babe");
        horse.setOfficialName("babette");
        horseService.createHorse(horse);

        ResponseEntity<HorseDTO> probe = horseController.getOneHorse(horse.getId());
        HorseDTO body = probe.getBody();

        assertEquals("campolina", body.getBreed());
        assertEquals("a5494867-d73a-4e8a-8620-0a3959710e9d", body.getGuid());
        assertEquals("babe", body.getNickName());
        assertEquals("babette", body.getOfficialName());
    }

    @Test
    public void testPersistHorse(){
        HorseDTO horseDTO = new HorseDTO();
        horseDTO.setBreed("dareshuri");
        horseDTO.setGuid("8f5cb89e-0a11-47eb-b0e6-f8a5e677deeb");
        horseDTO.setNickName("doerty");
        horseDTO.setOfficialName("doerte");

        ResponseEntity<HorseDTO> probe = horseController.persistHorse(horseDTO);
        HorseDTO horseDTO1 = probe.getBody();

        assertEquals("dareshuri", horseDTO1.getBreed());
        assertEquals("8f5cb89e-0a11-47eb-b0e6-f8a5e677deeb", horseDTO1.getGuid());
        assertEquals("doerty", horseDTO1.getNickName());
        assertEquals("doerte", horseDTO1.getOfficialName());
    }

    @Test
    public void testUpdateHorse(){
        Horse horse = new Horse();
        horse.setBreed("dareshuri");
        horse.setGuid("8f5cb89e-0a11-47eb-b0e6-f8a5e677deeb");
        horse.setNickName("doerty");
        horse.setOfficialName("doerte");

        Horse savedHorse = horseService.createHorse(horse);

        Horse updatedHorse = new Horse();
        updatedHorse.setId(horse.getId());
        updatedHorse.setBreed("east bulgarian");
        updatedHorse.setGuid("58d3f8e0-bcae-4a36-b035-52facdbf6401");
        updatedHorse.setNickName("batty");
        updatedHorse.setOfficialName("batman");

        HorseDTO horseDTO = horseMapper.toHorseDTO(updatedHorse);
        horseController.updateHorse(savedHorse.getId(), horseDTO);

        Optional<Horse> probe = horseService.getOneHorse(savedHorse.getId());

        assertEquals("east bulgarian", probe.get().getBreed());
        assertEquals("58d3f8e0-bcae-4a36-b035-52facdbf6401", probe.get().getGuid());
        assertEquals("batty", probe.get().getNickName());
        assertEquals("batman", probe.get().getOfficialName());
    }

    @Test
    public void testDeleteHorse(){
        Horse horse = new Horse();
        horse.setBreed("finnhorse");
        horse.setGuid("dd167202-9452-48d9-be32-d96ce8d7703c");
        horse.setNickName("phinny");
        horse.setOfficialName("phineas");

        Horse savedHorse = horseService.createHorse(horse);
        horseService.deleteHorse(horse.getId());

        Optional<Horse> deletedHorse = horseService.getOneHorse(savedHorse.getId());

        assertFalse(deletedHorse.isPresent());

    }
}
