package com.grupp5.accountmanager.repository;

import com.grupp5.accountmanager.models.UserM;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserM, Integer> {

}
