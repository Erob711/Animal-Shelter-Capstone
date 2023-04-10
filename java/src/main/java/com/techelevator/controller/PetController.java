package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.model.Pet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/pet")
@PreAuthorize("permitAll")
//TODO preAuthorize??
public class PetController {
    //private Pet pet;
    private final PetDao petDao;

    public PetController( PetDao petDao) {
        //this.pet = pet;
        this.petDao = petDao;
    }
    @RequestMapping(path="/all",method = RequestMethod.GET)
    public List<Pet> listAllPet(){
        //TODO list all pet from petDao.findAll()
        List<Pet> allPet = petDao.findAll();
        //TODO return List
        return allPet;

    }
    @RequestMapping(path="/{id}",method = RequestMethod.GET)
    public Pet listPet(@PathVariable("id") int petId){
        //TODO  get pet from petDao.findById()
        Pet pet = petDao.findById(petId);
        //TODO return List
        return pet;
    }


}
