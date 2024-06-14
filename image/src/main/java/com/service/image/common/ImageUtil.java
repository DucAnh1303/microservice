package com.service.image.common;

import com.data.entity.ImageEntity;
import com.service.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ImageUtil {

    private final ImageRepository repository;

    @Value("${image.url}")
    private String url;

    public String uploadImage(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();

            // Kiểm tra nếu url đã tồn tại
            Optional<ImageEntity> existingByUrl = repository.findByUrl(originalFilename);

            if (existingByUrl.isPresent()) {
                return  getImageUrl(existingByUrl.get().getUrl());
            }

            ImageEntity image = new ImageEntity();
            image.setImageName(file.getName());
            image.setByteCode(file.getBytes());
            image.setCreatedDate(LocalDateTime.now());
            image.setUrl(file.getOriginalFilename());
            repository.save(image);
            return getImageUrl(image.getUrl());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getImageUrl(String imageName) {
        return url + "/file/image/" + imageName;
    }


    public ResponseEntity<Resource> getImage(String fileName) {
        Optional<ImageEntity> imageOptional = repository.findByUrl(fileName);
        if (imageOptional.isPresent()) {

            ImageEntity image = imageOptional.get();
            ByteArrayResource resource = new ByteArrayResource(image.getByteCode());

            String contentType;
            if (image.getUrl().endsWith(".jpg") || image.getUrl().endsWith(".jpeg")) {
                contentType = MediaType.IMAGE_JPEG_VALUE;
            } else if (image.getUrl().endsWith(".png")) {
                contentType = MediaType.IMAGE_PNG_VALUE;
            } else {
                contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getUrl() + "\"")
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
