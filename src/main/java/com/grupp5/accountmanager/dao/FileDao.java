package com.grupp5.accountmanager.dao;

import com.grupp5.accountmanager.models.FileEntity;
import com.grupp5.accountmanager.models.UserM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDao extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findFileEntitiesByUploader(UserM user);
}
