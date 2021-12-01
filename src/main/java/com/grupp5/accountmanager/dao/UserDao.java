package com.grupp5.accountmanager.dao;

import com.grupp5.accountmanager.models.UserM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<UserM, Long> {

    Optional<UserM> findByUserEmail(String email);

}
