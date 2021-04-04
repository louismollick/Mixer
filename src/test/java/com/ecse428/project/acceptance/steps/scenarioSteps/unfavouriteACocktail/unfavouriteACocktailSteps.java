package com.ecse428.project.acceptance.steps.scenarioSteps.unfavouriteACocktail;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.Map;

import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class unfavouriteACocktailSteps {
    @Autowired
    private TestContext context;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @When("I request to unfavorite the cocktail")
    public void i_request_to_unfavorite_the_cocktail() {
        // Send request
        final String uri_req = "/api/user/{userId}/favouriteCocktail/{cocktailName}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", context.getUser().getId().toString());
        params.put("cocktailName", context.getSelectedCocktail());
        context.setResponse(restTemplate.exchange(uri_req, HttpMethod.DELETE, null, String.class, params));
    }

    @Then("the cocktail is removed from my favorites list")
    public void the_cocktail_is_removed_from_my_favorites_list() {
        // Check that the user's favorites list doesn't contain the selected Cocktail
        // name
        assertEquals(HttpStatus.OK, context.getResponse().getStatusCode());
        assertFalse(userRepository.findByEmail(context.getUser().getEmail()).get().getFavouriteCocktails().toString()
                .contains(context.getSelectedCocktail()));
    }

    @Then("my cocktail favorites list has not changed")
    public void my_cocktail_favorites_list_has_not_changed() {
        var olduser = context.getUser();
        var newuser = userRepository.findByEmail(olduser.getEmail()).get();
        assertEquals(olduser.getFavouriteCocktails(), newuser.getFavouriteCocktails());
    }
}
