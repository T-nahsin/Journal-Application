package com.tnahsin.journalApplication;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;


@EnableMongoRepositories(basePackages = "com.tnahsin.journalApplication.repository")
@ComponentScan(basePackages = "com.tnahsin.journalApplication")
@SpringBootApplication
public class JournalApplication {
	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);


	}




	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
