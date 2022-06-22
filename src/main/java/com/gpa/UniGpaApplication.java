package com.gpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.gpa.repository")
public class UniGpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniGpaApplication.class, args);
	}
	
	

}
