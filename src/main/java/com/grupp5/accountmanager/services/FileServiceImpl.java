package com.grupp5.accountmanager.services;

import com.grupp5.accountmanager.dao.FileDao;
import com.grupp5.accountmanager.dao.UserDao;
import com.grupp5.accountmanager.models.FileEntity;
import com.grupp5.accountmanager.models.UserM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class FileServiceImpl implements FileService{

    private final FileDao fileDao;
    private final UserDao userDao;

    @Autowired
    public FileServiceImpl(FileDao fileDao, UserDao userDao) {
        this.fileDao = fileDao;
        this.userDao = userDao;
    }

    private Optional<UserM> getUserByEmail(String userEmail) {
        return userDao.findByUserEmail(userEmail);
    }

    @Override
    public void save(MultipartFile file, String userEmail) throws IOException {

        Optional<UserM> user = getUserByEmail(userEmail); // Get user to set uploader

        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());
        fileEntity.setUploader(user.get());
        fileDao.save(fileEntity);
    }

    @Override
    public Optional<FileEntity> getFile(Long id, String userEmail) {
        Optional<FileEntity> fileEntity = fileDao.findById(id);
        System.out.println(fileEntity.get().getUploader().getUserEmail());
        if(fileEntity.isEmpty() || fileEntity.get().getUploader().getUserEmail() == null) {
            return fileEntity;
        } else if(Objects.equals(fileEntity.get().getUploader().getUserEmail(), userEmail)) {
            return fileEntity;
        }
        return fileDao.findById(id);
    }

    @Override
    @Transactional // Added to handle bigger LOB streams
    public HashMap<String, List> getFileList(String userEmail) {

        Optional<UserM> user = getUserByEmail(userEmail);

        HashMap<String, List> files = new HashMap<>();
        List<FileEntity> file = new ArrayList<>();

        fileDao.findFileEntitiesByUploader(user.get()).forEach(f -> {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setName(f.getName());
            fileEntity.setContentType(f.getContentType());
            fileEntity.setId(f.getId());
            fileEntity.setSize(f.getSize());
            file.add(fileEntity);
        });
        files.put("files", file);

        return files;
    }

    @Override
    public Boolean deleteFile(Long id) {
        Optional<FileEntity> fileEntity = fileDao.findById(id);

        if(fileEntity.isPresent()) {
            fileDao.delete(fileEntity.get());
        } else {
            throw new EntityNotFoundException("Entity not found");
        }

        return true;
    }
}
