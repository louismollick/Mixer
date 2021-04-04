package com.ecse428.project.acceptance.steps.scenarioSteps.favouriteACocktail;

import io.cucumber.java.en.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.repository.CocktailRepository;
import com.ecse428.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class favouriteACocktailSteps {
    @Autowired
    private TestContext context;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CocktailRepository cocktailRepository;

    @When("I select the cocktail {string}")
    public void i_select_the_cocktail(String selectedCocktail) {
        context.setSelectedCocktail(selectedCocktail);
    }

    @When("I request to favorite the cocktail")
    public void i_request_to_favorite_the_cocktail() {
        // Send request
        final String uri_req = "/api/user/{userId}/favouriteCocktail/{cocktailName}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", context.getUser().getId().toString());
        params.put("cocktailName", context.getSelectedCocktail());
        context.setResponse(restTemplate.exchange(uri_req, HttpMethod.PUT, null, String.class, params));
    }

    @Then("the cocktail is added to my favorites list")
    public void the_coctail_is_added_to_my_favorites_list() {
        // Check that the user's favorites list contains the selected Cocktail name
        assertEquals(HttpStatus.OK, context.getResponse().getStatusCode());
        assertTrue(userRepository.findByEmail(context.getUser().getEmail()).get().getFavouriteCocktails().toString()
                .contains(context.getSelectedCocktail()));
    }

    @Given("the cocktail {string} is already in my favorites list")
    public void the_cocktail_is_already_in_my_favorites_list(String cocktailName) {
        var user = context.getUser();
        var cocktail = cocktailRepository.findByName(cocktailName).get();
        user.addFavouriteCocktail(cocktail);
        userRepository.save(user);

        var newuser = userRepository.findByEmail(user.getEmail()).get();
        assertTrue(newuser.getFavouriteCocktails().toString().contains(cocktailName));
        context.setUser(newuser);
    }

    @Then("the cocktail is kept in my favorites list")
    public void the_cocktail_is_kept_in_my_favorites_list() {
        assertEquals(HttpStatus.OK, context.getResponse().getStatusCode());
        assertTrue(userRepository.findByEmail(context.getUser().getEmail()).get().getFavouriteCocktails().toString()
                .contains(context.getSelectedCocktail()));
    }

    @Then("the cocktail is not added to my favorites list")
    public void the_cocktail_is_not_added_to_my_favorites_list() {
        assertFalse(userRepository.findByEmail(context.getUser().getEmail()).get().getFavouriteCocktails().toString()
                .contains(context.getSelectedCocktail()));
    }
}
