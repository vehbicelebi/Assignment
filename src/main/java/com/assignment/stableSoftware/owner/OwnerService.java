package com.assignment.stableSoftware.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Optional<Owner> getOneOwner(Long id){
        return ownerRepository.findById(id);
    }

    public List<Owner> getAllOwner(){
        return ownerRepository.findAll();
    }

    public Owner createOwner(Owner owner){
        return ownerRepository.save(owner);
    }

    public Owner updateOwner(Long id, Owner ownerDetails){
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner not fount"));

        owner.setName(ownerDetails.getName());
        owner.setHorses(ownerDetails.getHorses());

        return ownerRepository.save(owner);
    }

    public void deleteOwner(Long id){
        ownerRepository.deleteById(id);
    }
}
