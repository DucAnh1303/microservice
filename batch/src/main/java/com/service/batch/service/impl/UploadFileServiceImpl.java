package com.service.batch.service.impl;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.Upload;
import com.service.batch.config.s3.S3Config;
import com.service.batch.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.awscore.AwsClient;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class UploadFileServiceImpl implements UploadService {

    @Value("${spring.cloud.aws.bucket.name}")
    private String buckets;

    @Autowired
    private AmazonS3 s3Client;

    @Override
    public String upload(MultipartFile file) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = dateFormat.format(currentDate) + "_" + file.getOriginalFilename();
        if (s3Client.doesObjectExist(buckets, fileName)) {
            return "File already exists: " + fileName;
        }
        File convertedFile = uploadFile(file);
        s3Client.putObject(new PutObjectRequest(buckets, fileName, convertedFile));
        convertedFile.delete();
        log.info("File uploaded: {}", fileName);
        return fileName;
    }

    private File uploadFile(MultipartFile file) {
        File convertFile = new File(System.currentTimeMillis() + "_" + file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertFile)) {
            fos.write(file.getBytes());
            return convertFile;
        } catch (IOException e) {
            log.error("Error while uploading file: {}", e.getMessage());
            return null;
        }
    }
}
