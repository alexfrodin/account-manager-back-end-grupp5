package com.grupp5.accountmanager.services;

import com.grupp5.accountmanager.models.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface FileService {
    void save(MultipartFile file, String userEmail) throws IOException;
    Optional<FileEntity> getFile(Long id, String userEmail);
    HashMap<String, List> getFileList(String userEmail);
}
