package com.service.microservice.batch.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String upload(MultipartFile file);
}
