package com.grupp5.accountmanager.services;

import com.grupp5.accountmanager.models.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface FileService {
    void save(MultipartFile file) throws IOException;
    Optional<FileEntity> getFile(Long id);
    Iterable<FileEntity> getAllFiles();
}
