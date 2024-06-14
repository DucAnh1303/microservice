package com.service.image;

import com.service.image.common.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class Controller {

    private final ImageUtil imageUtil;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile file) {
        return imageUtil.uploadImage(file);
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        return imageUtil.getImage(imageName);
    }
}
