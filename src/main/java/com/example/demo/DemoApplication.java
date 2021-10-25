package com.example.demo;

import com.example.demo.service.file.storage.FileStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Resource
	private FileStorageService storageService;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
<<<<<<< Updated upstream

	@Override
	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
=======
>>>>>>> Stashed changes
}
