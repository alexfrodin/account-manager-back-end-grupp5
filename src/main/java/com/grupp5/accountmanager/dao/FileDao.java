package com.grupp5.accountmanager.dao;

import com.grupp5.accountmanager.models.FileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDao extends CrudRepository<FileEntity, Long> {
}
