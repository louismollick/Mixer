package com.ecse428.project.unit.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.ecse428.project.model.Modifier;
import com.ecse428.project.repository.CocktailRepository;
import com.ecse428.project.repository.ModifierRepository;
import com.ecse428.project.model.User;
import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.Cocktail;

import org.junit.Assert;
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

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

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

    @MockBean
    private SecurityContext securityContext;

    @MockBean
    private Authentication authentication;

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
  
    @Test
    public void removeFavouriteCocktail() {
        long id = 22;
        Set<Modifier> modifiersInInventory = new HashSet<Modifier>();
        Set<Alcohol> alcoholInInventory = new HashSet<Alcohol>();
        Set<Cocktail> favouriteCocktails = new HashSet<Cocktail>();
        User newUser = new User(id,"test@gmail.com","888888", alcoholInInventory, modifiersInInventory);
        Cocktail mimosa = new Cocktail();
        mimosa.setName("Mimosa");
        favouriteCocktails.add(mimosa);
        newUser.setFavouriteCocktails(favouriteCocktails);

        given(userRepository.findById(id)).willReturn(Optional.of(newUser));
        given(cocktailRepository.findByName("Mimosa")).willReturn(Optional.of(mimosa));

        userService.deleteFavouriteCocktail(id, "Mimosa");
        assertFalse(newUser.getFavouriteCocktails().contains(mimosa));
    }

    @Test
    public void addFavouriteCocktail() {
        long id = 22;
        Set<Modifier> modifiersInInventory = new HashSet<Modifier>();
        Set<Alcohol> alcoholInInventory = new HashSet<Alcohol>();
        Set<Cocktail> favouriteCocktails = new HashSet<Cocktail>();
        User newUser = new User(id,"test@gmail.com","888888", alcoholInInventory, modifiersInInventory);
        Cocktail sangria = new Cocktail();
        sangria.setName("Sangria");
        favouriteCocktails.add(sangria);
        newUser.setFavouriteCocktails(favouriteCocktails);

        given(userRepository.findById(id)).willReturn(Optional.of(newUser));
        given(cocktailRepository.findByName("Sangria")).willReturn(Optional.of(sangria));

        userService.putFavouriteCocktail(id, "Sangria");
        assertTrue(newUser.getFavouriteCocktails().contains(sangria));
    }

    @Test
    public void deleteAccount() {
        long id = 53;
        Set<Modifier> modifiers = new HashSet<Modifier>();
        Set<Alcohol> alcohols = new HashSet<Alcohol>();
        String email = "sample.email@gmail.com";
        User newUser = new User(id,email, "987654321", alcohols, modifiers);

        given(userRepository.findById(id)).willReturn(Optional.of(newUser));
        given(userRepository.findByEmail(email)).willReturn(Optional.of(newUser));
        given(securityContext.getAuthentication()).willReturn(authentication);
        given(authentication.getPrincipal()).willReturn(email);
        SecurityContextHolder.setContext(securityContext);

        userService.deleteAccount(newUser.getId());
        Assert.assertNotNull(userRepository.findById(id));
    }
}