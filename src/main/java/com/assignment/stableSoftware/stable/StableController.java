package com.assignment.stableSoftware.stable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stables")
public class StableController {
    private StableService stableService;
    private StableMapper stableMapper;

    @Autowired
    public StableController(StableService stableService, StableMapper stableMapper) {
        this.stableService = stableService;
        this.stableMapper = stableMapper;
    }

    @GetMapping
    public ResponseEntity<List<StableDTO>> getStables(){
        List<Stable> stables = stableService.getAllStables();
        List<StableDTO> stableDTOS = stableMapper.toStableDTOs(stables);

        return ResponseEntity.ok(stableDTOS);
    }

    @GetMapping("/{stableId}")
    public ResponseEntity<StableDTO> getOneStable(@PathVariable("stableId") Long id){
        Optional<Stable> stable = stableService.getOneStable(id);

        if(stable.isPresent()){
            StableDTO stableDTO = stableMapper.toStableDTO(stable.get());
            return ResponseEntity.ok(stableDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<StableDTO> persistStable(@RequestBody StableDTO stableDTO){
        Stable stable = stableMapper.toStable(stableDTO);
        Stable createdStable = stableService.createStable(stable);
        StableDTO createdStableDTO = stableMapper.toStableDTO(createdStable);

        return ResponseEntity.ok(createdStableDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StableDTO> updateStable(@PathVariable Long id, @RequestBody StableDTO stableDTO){
        Stable stableDetails = stableMapper.toStable(stableDTO);
        Stable updatedStable = stableService.updateStable(id, stableDetails);
        StableDTO updatedStableDTO = stableMapper.toStableDTO(updatedStable);

        return ResponseEntity.ok(updatedStableDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorse(@PathVariable Long id){
        stableService.deleteStable(id);
        return ResponseEntity.noContent().build();
    }

}
