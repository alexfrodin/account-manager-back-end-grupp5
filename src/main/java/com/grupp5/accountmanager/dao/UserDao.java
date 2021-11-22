package com.grupp5.dao;

import com.grupp5.models.UserM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserM, Long> {


}
