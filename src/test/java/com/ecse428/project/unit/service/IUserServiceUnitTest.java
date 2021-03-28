package com.ecse428.project.unit.service;

import static org.junit.Assert.assertFalse;

import com.ecse428.project.model.Modifier;
import com.ecse428.project.repository.CocktailRepository;
import com.ecse428.project.repository.ModifierRepository;
import com.ecse428.project.model.User;
import com.ecse428.project.model.Alcohol;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecse428.project.repository.AlcoholRepository;
import com.ecse428.project.repository.UserRepository;
import com.ecse428.project.service.IUserService;
import com.ecse428.project.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.mockito.BDDMockito.given;
import java.util.*;


@RunWith(SpringRunner.class)
public class IUserServiceUnitTest {

    @Autowired
    private IUserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ModifierRepository modifierRepository;

    @MockBean
    private CocktailRepository cocktailRepository;

    @MockBean
    private AlcoholRepository a;

    @MockBean
    private BCryptPasswordEncoder b;

    @TestConfiguration
    static class IUserServiceTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new IUserService();
        }
    }


    @Test
    public void removeModifier_Inventory() {
        long id = 22;
        var name = "DietCoke";
        Modifier dietcoke = new Modifier(name, Modifier.ModifierType.SMOOTHING_AGENT);
        Set<Modifier> modifiersInInventory = new HashSet<Modifier>();
        modifiersInInventory.add(dietcoke);
        Set<Alcohol> alcoholInInventory = new HashSet<Alcohol>();
        User newUser = new User(id,"test@gmail.com","888888", alcoholInInventory, modifiersInInventory);

        given(userRepository.findById(id)).willReturn(Optional.of(newUser));
        given(modifierRepository.findByName(name)).willReturn(Optional.of(dietcoke));

        userService.deleteModifierInInventory(id, "DietCoke");
        assertFalse(newUser.getModifiersInInventory().contains(dietcoke));
    }

    @Test
    public void removeAlcohol_Inventory() {
        long id = 22;
        var name = "Vodka";
        Alcohol vodka = new Alcohol(name);
        Set<Modifier> modifiersInInventory = new HashSet<Modifier>();
        Set<Alcohol> alcoholsInInventory = new HashSet<Alcohol>();
        alcoholsInInventory.add(vodka);
        User newUser = new User(id,"user@gmail.com","123456789", alcoholsInInventory, modifiersInInventory);

        given(userRepository.findById(id)).willReturn(Optional.of(newUser));
        given(a.findByName(name)).willReturn(Optional.of(vodka));

        userService.deleteAlcoholInInventory(id, "Vodka");
        assertFalse(newUser.getAlcoholInInventory().contains(vodka));
    }
  
}