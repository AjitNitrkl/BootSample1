package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
public class BootSampleEx2Application {

	public static void main(String[] args) {
		SpringApplication.run(BootSampleEx2Application.class, args);
	}
}