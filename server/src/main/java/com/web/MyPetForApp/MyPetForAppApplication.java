package com.web.MyPetForApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyPetForAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPetForAppApplication.class, args);
	}
}
