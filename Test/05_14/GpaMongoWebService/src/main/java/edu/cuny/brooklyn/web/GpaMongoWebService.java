package edu.cuny.brooklyn.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class GpaMongoWebService {

	public static void main(String[] args) {
		SpringApplication.run(GpaMongoWebService.class, args);
	}
}
