package com.ecse428.project.config;

import com.ecse428.project.model.User;
import com.ecse428.project.service.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(UserService service) {
        return args -> {
            User example = new User("user@example.com", "12345678");

            service.postSignup(example); // this also encrypts the password
        };
    }
}