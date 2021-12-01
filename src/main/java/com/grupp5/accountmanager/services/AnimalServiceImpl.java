package com.grupp5.accountmanager.services;

import com.grupp5.accountmanager.dao.AnimalDao;
import com.grupp5.accountmanager.models.Animal;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.mongodb.util.BsonUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.Collection;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService  {

    @Autowired
    private AnimalDao animalDao;



    @Override
    public Animal getAnimalById(Long id) {
        return animalDao.findById(id).orElseThrow();
    }

    @Override
    public Animal createAnimal(Animal animal) {

        animalDao.save(animal);
        return animal;
    }
}
