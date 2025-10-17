package com.mybank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.mybank.entity")
@ComponentScan(basePackages = {"com.mybank.security","com.mybank.service"})
public class MybankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybankApplication.class, args);
	}

}
