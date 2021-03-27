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
            User john = new User("JohnDoe9@gmail.com", "12345678");

            service.postSignup(john); // this also encrypts the password
        };
    }
}