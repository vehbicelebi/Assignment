package com.assignment.stableSoftware.horse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/horses")
public class HorseController {

    private HorseService horseService;
    private HorseMapper horseMapper;

    @Autowired
    public HorseController(HorseService horseService, HorseMapper horseMapper) {
        this.horseService = horseService;
        this.horseMapper = horseMapper;
    }

    @GetMapping
    public ResponseEntity<List<HorseDTO>> getHorses(){
        List<Horse> horses = horseService.getAllHorses();
        List<HorseDTO> horseDTOs = horseMapper.toHorseDTOs(horses);

        return ResponseEntity.ok(horseDTOs);
    }

    @GetMapping("/{horseId}")
    public ResponseEntity<HorseDTO> getOneHorse(@PathVariable("horseId") Long id){
        Optional<Horse> horse = horseService.getOneHorse(id);

        if(horse.isPresent()){
            HorseDTO horseDTO = horseMapper.toHorseDTO(horse.get());
            return ResponseEntity.ok(horseDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<HorseDTO> persistHorse(@RequestBody HorseDTO horseDTO){
        Horse horse = horseMapper.toHorse(horseDTO);
        Horse createdHorse = horseService.createHorse(horse);
        HorseDTO createdHorseDTO = horseMapper.toHorseDTO(createdHorse);

        return ResponseEntity.ok(createdHorseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorseDTO> updateHorse(@PathVariable Long id, @RequestBody HorseDTO horseDTO){
        Horse horseDetails = horseMapper.toHorse(horseDTO);
        Horse updatedHorse = horseService.updateHorse(id, horseDetails);
        HorseDTO updatedHorseDTO = horseMapper.toHorseDTO(updatedHorse);

        return ResponseEntity.ok(updatedHorseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorse(@PathVariable Long id){
        horseService.deleteHorse(id);
        return ResponseEntity.noContent().build();
    }
}
