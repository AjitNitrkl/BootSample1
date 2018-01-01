package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.example")
@EnableScheduling
public class BootSampleEx11Application extends org.springframework.boot.web.support.SpringBootServletInitializer {

	public static void main(String[] args) {
		
		SpringApplication.run(BootSampleEx11Application.class, args);
		
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BootSampleEx11Application.class);
	}
}
