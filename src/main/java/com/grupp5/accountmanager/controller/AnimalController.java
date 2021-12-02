package com.grupp5.accountmanager.controller;

import com.grupp5.accountmanager.models.Animal;
import com.grupp5.accountmanager.services.AnimalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnimalController {

    @Value("${app.base.url}")
    private String base_url;

    @Autowired
    private AnimalServiceImpl animalService;

    @GetMapping("/api/animal/{id}")
    public ResponseEntity<Animal> findById(@PathVariable Long id) {
        Animal animal = animalService.getAnimalById(id);
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    @PostMapping("/api/animal/create")
    public ResponseEntity<Animal> createUser(@RequestBody Animal animal) {
        Animal createdAnimal = animalService.createAnimal(animal);
        return new ResponseEntity<>(createdAnimal, HttpStatus.CREATED);
    }
}
