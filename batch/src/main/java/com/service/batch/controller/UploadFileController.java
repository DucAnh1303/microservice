package com.service.batch.controller;

import com.service.batch.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadFileController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("upload-s3")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return uploadService.upload(file);
    }

}


