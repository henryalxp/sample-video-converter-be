package com.wfs.fileconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FileConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileConverterApplication.class, args);
	}

}
