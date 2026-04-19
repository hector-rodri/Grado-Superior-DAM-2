package com.example;

import org.springframework.boot.SpringApplication;//Import all libraries
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//Annotation to indicate that this is a Spring Boot application
public class MircroservicescrudApplication {
	public static void main(String[] args) {
		SpringApplication.run(MircroservicescrudApplication.class, args);//Run the application
	}
}
