package com.assignment.stableSoftware.stable;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StableMapper {
    public StableDTO toStableDTO(Stable stable){
        if(stable == null){
            return null;
        }
        StableDTO stableDTO = new StableDTO();
        stableDTO.setName(stable.getName());
        stableDTO.setLocation(stable.getLocation());

        return stableDTO;
    }

    public Stable toStable(StableDTO stableDTO){
        if (stableDTO == null){
            return null;
        }

        Stable stable = new Stable();
        stable.setName(stableDTO.getName());
        stable.setLocation(stableDTO.getLocation());

        return stable;
    }

    public List<StableDTO> toStableDTOs(List<Stable> stables){
        if (stables == null){
            return null;
        }

        List<StableDTO> stableDTOS = new ArrayList<>();

        for (Stable stable : stables){
            stableDTOS.add(toStableDTO(stable));
        }

        return stableDTOS;
    }
}
