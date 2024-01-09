package com.example.pagebackend;

import com.example.mdbspringboot.userdata.UserModel;
import com.example.mdbspringboot.userdata.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class PageBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.example.mdbspringboot.pagebackend.PageBackendApplication.class, args);
	}



	@Bean
	CommandLineRunner runner(UserRepository repository) {
		return args -> {
			UserModel userModel = new UserModel("lucas", "lucas@gmail.com", "1234");
			repository.insert(userModel);
		};
	}
}
