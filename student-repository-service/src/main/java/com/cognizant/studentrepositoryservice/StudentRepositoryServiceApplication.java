package com.cognizant.studentrepositoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StudentRepositoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentRepositoryServiceApplication.class, args);
	}

}
