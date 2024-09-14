package com.service.batch.controller;

import com.service.batch.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class UploadFileController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("upload-s3")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return uploadService.upload(file);
    }

    public static void main(String[] args) {
        // Tạo một TreeMap với các khóa và giá trị kiểu String
        TreeMap<String, Integer> treeMap = new TreeMap<>(Comparator.reverseOrder());

        // Thêm các phần tử vào TreeMap
        treeMap.put("Orange", 5);
        treeMap.put("Apple", 10);
        treeMap.put("Banana", 20);
        treeMap.put("Grapes", 15);

        // In TreeMap để thấy các phần tử được sắp xếp theo thứ tự khóa (tự nhiên)
        System.out.println("TreeMap (sắp xếp theo thứ tự tự nhiên của khóa):");
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Tìm kiếm một giá trị theo khóa
        System.out.println("\nSố lượng táo (Apple): " + treeMap.get("Apple"));

        // Xóa một phần tử bằng khóa
        treeMap.remove("Banana");

        // In TreeMap sau khi xóa
        System.out.println("\nTreeMap sau khi xóa Banana:");
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}


