package com.grupp5.accountmanager.services;

import com.grupp5.accountmanager.dao.FileDao;
import com.grupp5.accountmanager.models.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService{

    private final FileDao fileDao;

    @Autowired
    public FileServiceImpl(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    @Override
    public void save(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());
        fileDao.save(fileEntity);
    }

    @Override
    public Optional<FileEntity> getFile(Long id) {
        return fileDao.findById(id);
    }

    @Override
    public Iterable<FileEntity> getAllFiles() {
        return fileDao.findAll();
    }
}
