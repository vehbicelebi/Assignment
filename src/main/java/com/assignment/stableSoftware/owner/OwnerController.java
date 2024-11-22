package com.assignment.stableSoftware.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private OwnerService ownerService;
    private OwnerMapper ownerMapper;

    @Autowired
    public OwnerController(OwnerService ownerService, OwnerMapper ownerMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
    }

    @GetMapping
    public ResponseEntity<List<OwnerDTO>> getOwners(){
        List<Owner> owners = ownerService.getAllOwner();
        List<OwnerDTO> ownerDTOS = ownerMapper.toOwnerDTOs(owners);

        return ResponseEntity.ok(ownerDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> getOneOwner(@PathVariable("id") Long id){
        Optional<Owner> owner = ownerService.getOneOwner(id);

        if(owner.isPresent()){
            OwnerDTO ownerDTO = ownerMapper.toOwnerDTO(owner.get());
            return ResponseEntity.ok(ownerDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<OwnerDTO> persistOwner(@RequestBody OwnerDTO ownerDTO){
        Owner owner = ownerMapper.toOwner(ownerDTO);
        Owner createdOwner = ownerService.createOwner(owner);

        OwnerDTO createdOwnerDTO = ownerMapper.toOwnerDTO(createdOwner);

        return ResponseEntity.ok(createdOwnerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerDTO> updateOwner(@PathVariable Long id, @RequestBody OwnerDTO ownerDTO){
        Owner ownerDetails = ownerMapper.toOwner(ownerDTO);
        Owner updatedOwner = ownerService.updateOwner(id, ownerDetails);
        OwnerDTO updatedOwnerDTO = ownerMapper.toOwnerDTO(updatedOwner);

        return ResponseEntity.ok(updatedOwnerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorse(@PathVariable Long id){
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
}
