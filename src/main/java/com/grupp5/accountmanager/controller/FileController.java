package com.grupp5.accountmanager.controller;

import com.grupp5.accountmanager.models.FileEntity;
import com.grupp5.accountmanager.services.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileServiceImpl fileService;

    @Autowired
    public FileController(FileServiceImpl fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, @RequestAttribute String userEmail) {
        try {
            fileService.save(file, userEmail);
            return ResponseEntity.status(HttpStatus.CREATED).body("File Uploaded" + file.getOriginalFilename());
        } catch(IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File not uploaded");
        }
    }

    @GetMapping(value = "/filesList", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, List> getFileList(@RequestAttribute String userEmail) {
        return  fileService.getFileList(userEmail);
    }

    @GetMapping("/{id}")
    public ResponseEntity getFileById(@RequestAttribute String userEmail, @PathVariable Long id) {
        Optional<FileEntity> fileEntity = fileService.getFile(id, userEmail);
        if(fileEntity.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No file found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(fileEntity.get());
        }
    }

}
