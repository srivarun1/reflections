package com.api.reflections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.*")
@SpringBootApplication
public class ReflectionsApplication {


	public static void main(String[] args) {
		SpringApplication.run(ReflectionsApplication.class, args);
	}

}
