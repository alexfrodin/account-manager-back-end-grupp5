package com.grupp5.accountmanager.controller;

import com.grupp5.accountmanager.services.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileServiceImpl fileService;

    @Autowired
    public FileController(FileServiceImpl fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            fileService.save(file);
            return ResponseEntity.status(HttpStatus.CREATED).body("File Uploaded" + file.getOriginalFilename());
        } catch(IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File not uploaded");
        }
    }

    @GetMapping(value = "/filesList", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, List> getFileList(@RequestAttribute String userEmail) {
        System.out.println(userEmail);
        return  fileService.getFileList();
    }

}
