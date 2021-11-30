package com.grupp5.accountmanager.dao;

import com.grupp5.accountmanager.models.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDao extends JpaRepository<FileEntity, Long> {
}
