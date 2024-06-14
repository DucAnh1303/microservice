package com.service.image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.data")
public class ImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageApplication.class, args);
	}

}
