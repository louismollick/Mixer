package com.ecse428.project.unit.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.ecse428.project.model.Modifier;
import com.ecse428.project.repository.ModifierRepository;
import com.ecse428.project.service.IModifierService;
import com.ecse428.project.model.User;
import com.ecse428.project.model.Alcohol;
import com.ecse428.project.service.ModifierService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.ecse428.project.model.User;
import com.ecse428.project.repository.AlcoholRepository;
import com.ecse428.project.repository.UserRepository;
import com.ecse428.project.service.IUserService;
import com.ecse428.project.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.BDDMockito.given;
import java.util.*;

@RunWith(SpringRunner.class)
public class DeleteAccountTest {

    @Autowired
    private IUserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ModifierRepository modifierRepository;

    @MockBean
    private AlcoholRepository a;

    @MockBean
    private BCryptPasswordEncoder b;

    @TestConfiguration
    static class DeleteAccountTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new IUserService();
        }
    }


    @Test(expected = ResponseStatusException.class)
    public void deleteExistingSignup(){
        long id = 53;
        Set<Modifier> modifiers = new HashSet<Modifier>();
        Set<Alcohol> alcohols = new HashSet<Alcohol>();
        String email = "sample.email@gmail.com";
        User newUser = new User(id,email, "987654321", alcohols, modifiers);

        userService.postSignup(newUser);

        userService.deleteAccount(id);

        userRepository.findById(id);
    }

    @Test(expected = ResponseStatusException.class)
    public void deleteNonExistentSignup(){
        long id = 53;
        Set<Modifier> modifiers = new HashSet<Modifier>();
        Set<Alcohol> alcohols = new HashSet<Alcohol>();
        String email = "sample.email@gmail.com";
        User newUser = new User(id,email, "987654321", alcohols, modifiers);

        userService.deleteAccount(id);

    }

}
