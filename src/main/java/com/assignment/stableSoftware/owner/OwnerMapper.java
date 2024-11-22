package com.assignment.stableSoftware.owner;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OwnerMapper {

    public OwnerDTO toOwnerDTO(Owner owner){
        if (owner == null)
            return null;

        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setName(owner.getName());

        return ownerDTO;
    }

    public Owner toOwner(OwnerDTO ownerDTO){
        if (ownerDTO == null)
            return null;

        Owner owner = new Owner();
        owner.setName(ownerDTO.getName());

        return owner;
    }

    public List<OwnerDTO> toOwnerDTOs(List<Owner> owners) {
        if (owners == null)
            return null;

        List<OwnerDTO> ownerDTOS = new ArrayList<>();

        for (Owner owner : owners){
            ownerDTOS.add(toOwnerDTO(owner));
        }

        return ownerDTOS;
    }
}
