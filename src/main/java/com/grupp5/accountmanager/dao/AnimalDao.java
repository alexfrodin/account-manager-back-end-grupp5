package com.grupp5.accountmanager.dao;

import com.grupp5.accountmanager.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalDao extends JpaRepository<Animal, Long> {


}
