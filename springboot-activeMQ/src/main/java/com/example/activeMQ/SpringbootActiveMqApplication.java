package com.example.activeMQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
public class SpringbootActiveMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootActiveMqApplication.class, args);
	}

}
