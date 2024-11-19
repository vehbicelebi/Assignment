package com.assignment.stableSoftware.stable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StableService {
    private StableRepository stableRepository;

    @Autowired
    public StableService(StableRepository stableRepository) {
        this.stableRepository = stableRepository;
    }

    public Optional<Stable> getOneStable(Long id){
        return stableRepository.findById(id);
    }

    public List<Stable> getAllStables(){
        return stableRepository.findAll();
    }

    public Stable createStable(Stable stable){
        return stableRepository.save(stable);
    }

    public Stable updateStable(Long id, Stable stableDetails){
        Stable stable = stableRepository.findById(id).orElseThrow(() -> new RuntimeException("Stable not found"));

        stable.setName(stableDetails.getName());
        stable.setLocation(stableDetails.getLocation());
        stable.setHorses(stableDetails.getHorses());

        return stableRepository.save(stable);
    }

    public void deleteStable(Long id){
        stableRepository.deleteById(id);
    }
}
