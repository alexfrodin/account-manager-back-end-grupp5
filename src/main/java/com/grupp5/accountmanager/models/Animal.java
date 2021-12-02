package com.grupp5.accountmanager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Animal {

    @Id
    @GeneratedValue
    private long Id;

    @Column(name="ANIMAL_NAME")
    private String animalName;
    @Column(name="ANIMAL_TYPE")
    private String typeOfAnimal;
    @Column(name="ANIMAL_AGE")
    private Float animalAge;

    public Animal() {
    }

    public Animal(String animalName, String typeOfAnimal, Float animalAge) {
        this.animalName = animalName;
        this.typeOfAnimal = typeOfAnimal;
        this.animalAge = animalAge;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public void setTypeOfAnimal(String typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
    }

    public Float getAnimalAge() {
        return animalAge;
    }

    public void setAnimalAge(Float animalAge) {
        this.animalAge = animalAge;
    }
}
