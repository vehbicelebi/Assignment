package com.assignment.stableSoftware.horse;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HorseMapper {

    public HorseDTO toHorseDTO(Horse horse){
        if(horse == null){
            return null;
        }
        HorseDTO horseDTO = new HorseDTO();
        horseDTO.setGuid(horse.getGuid());
        horseDTO.setOfficialName(horse.getOfficialName());
        horseDTO.setNickName(horse.getNickName());
        horseDTO.setBreed(horse.getBreed());

        return horseDTO;
    }

    public Horse toHorse(HorseDTO horseDTO){
        if(horseDTO == null){
            return null;
        }

        Horse horse = new Horse();
        horse.setGuid(horseDTO.getGuid());
        horse.setOfficialName(horseDTO.getOfficialName());
        horse.setNickName(horseDTO.getNickName());
        horse.setBreed(horseDTO.getBreed());

        return horse;
    }

    public List<HorseDTO> toHorseDTOs(List<Horse> horses){
        if(horses == null){
            return null;
        }

        List<HorseDTO> horseDTOs = new ArrayList<>();
        for (Horse horse : horses){
            horseDTOs.add(toHorseDTO(horse));
        }

        return horseDTOs;
    }
}
