package com.grupp5.accountmanager.controller;

import com.grupp5.accountmanager.models.FileEntity;
import com.grupp5.accountmanager.services.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.FileCopyUtils.BUFFER_SIZE;

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

        response.setContentType(fileEntity.get().getContentType());
        response.setHeader(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\"" + fileEntity.get().getName() + "\""
        );

        return ResponseEntity.ok().body(fileEntity.get().getData());

        // return outputStream -> {
        //     int bytesRead;
        //     byte[] buffer = fileEntity.get().getData();
        //     ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
        //     while ((bytesRead = byteArrayInputStream.read()) != -1) {
        //         outputStream.write(buffer, 0, bytesRead);
        //     }
        // };


    }

}
