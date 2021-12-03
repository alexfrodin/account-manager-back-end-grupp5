package com.grupp5.accountmanager.controller;

import com.grupp5.accountmanager.models.FileEntity;
import com.grupp5.accountmanager.services.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
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

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, @RequestAttribute String userEmail) {
        try {
            fileService.save(file, userEmail);
            return ResponseEntity.status(HttpStatus.CREATED).body("File Uploaded " + file.getOriginalFilename());
        } catch(IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File not uploaded");
        }
    }

    @GetMapping(value = "/filesList", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, List> getFileList(@RequestAttribute String userEmail) {
        return  fileService.getFileList(userEmail);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFileById(HttpServletResponse response, @RequestAttribute String userEmail, @PathVariable Long id) {
        Optional<FileEntity> fileEntity = fileService.getFile(id, userEmail);

        // response.setContentType(fileEntity.get().getContentType());
        // response.setHeader(
        //         HttpHeaders.CONTENT_DISPOSITION,
        //         "attachment;filename=\"" + fileEntity.get().getName() + "\""
        // );

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", fileEntity.get().getContentType());
        responseHeaders.add("Content-Disposition", "attachment;filename=\"" + fileEntity.get().getName() + "\"");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(fileEntity.get().getData());

        // return outputStream -> {
        //     int bytesRead;
        //     byte[] buffer = fileEntity.get().getData();
        //     ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
        //     while ((bytesRead = byteArrayInputStream.read()) != -1) {
        //         outputStream.write(buffer, 0, bytesRead);
        //     }
        // };

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        Boolean isDeleted = fileService.deleteFile(id);
        if(!isDeleted) {
            throw new EntityNotFoundException("No entity found");
        }
        return ResponseEntity.ok().body("File deleted");
    }

}
