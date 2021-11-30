package com.grupp5.accountmanager.dao;

import com.grupp5.accountmanager.models.UserM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<UserM, Long> {

    UserM findByUserEmail(String email);

}
