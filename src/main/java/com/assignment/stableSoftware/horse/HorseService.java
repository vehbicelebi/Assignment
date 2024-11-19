package com.assignment.stableSoftware.horse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class HorseService {

    private HorseRepository horseRepository;

    @Autowired
    public HorseService(HorseRepository horseRepository) {
        this.horseRepository = horseRepository;
    }

    public Optional<Horse> getOneHorse(Long id){
        return horseRepository.findById(id);
    }

    public List<Horse> getAllHorses(){
        return horseRepository.findAll();
    }

    public Horse createHorse(Horse horse){
        return horseRepository.save(horse);
    }

    public Horse updateHorse(Long id, Horse horseDetails){
        Horse horse = horseRepository.findById(id).orElseThrow(() -> new RuntimeException("Horse not found"));

        horse.setGuid(horseDetails.getGuid());
        horse.setOfficialName(horseDetails.getOfficialName());
        horse.setNickName(horseDetails.getNickName());
        horse.setBreed(horseDetails.getBreed());
        horse.setOwner(horseDetails.getOwner());
        horse.setStable(horseDetails.getStable());
        horse.setFoods(horseDetails.getFoods());
        horse.setFeedingTimes(horseDetails.getFeedingTimes());

        return horseRepository.save(horse);
    }

    public void deleteHorse(Long id){
        horseRepository.deleteById(id);
    }

}
