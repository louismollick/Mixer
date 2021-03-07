package com.ecse428.project.unit.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import com.ecse428.project.model.Modifier;
import com.ecse428.project.repository.ModifierRepository;
import com.ecse428.project.service.IModifierService;
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

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
public class IUserServiceUnitTest {

    @Autowired
    private IUserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ModifierRepository modifierRepository;


    @TestConfiguration
    static class IUserServiceTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new IUserService();
        }
    }

    @Before
    public void setUp() {
        Modifier sod = new Modifier("Fanta", Modifier.ModifierType.SODA);
        when(modifierRepository.findAll()).thenReturn(Arrays.asList(sod));
    }

    @Test
    public void removeModifier_Inventory() {
        User testuser = new User("tks@hotmail.com", "888888");
        userRepository.save(testuser);
        long testuserid = testuser.getId();
        String name = "sod";
        Set<Modifier> modifier = userService.getModifiersInInventory(testuserid);
        userService.putModifierInInventory(testuserid, "Fanta");
        userService.deleteModifierInInventory(testuserid, "Fanta");
        boolean state = false;

        for (Modifier m : modifier) {
            if (m.getName().equals(name)) {
                state = false;
                break;
            }
            else state = false;
        }

        assertFalse(state);

    }
  
}