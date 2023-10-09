package com.madfofo.quizzservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class QuizzServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizzServiceApplication.class, args);
	}

}
