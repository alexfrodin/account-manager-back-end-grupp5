package com.grupp5.accountmanager.services;

import com.grupp5.accountmanager.models.Animal;

public interface AnimalService {

    Animal getAnimalById(Long id);

    Animal createAnimal(Animal animal);
}
