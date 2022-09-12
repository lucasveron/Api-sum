package com.tenpo.Apisum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class ApiSumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSumApplication.class, args);
	}

}
