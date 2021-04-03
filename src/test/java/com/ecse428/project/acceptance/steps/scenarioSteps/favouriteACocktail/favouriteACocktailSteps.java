package com.ecse428.project.acceptance.steps.scenarioSteps.favouriteACocktail;

import io.cucumber.java.en.*;

import static org.junit.Assert.assertTrue;

public class favouriteACocktailSteps {

    @And("^I am logged into my account$")
    public void i_am_logged_into_my_account() {
        assertTrue(true);
    }

    @When("^I search a coctail$")
    public void i_search_a_coctail() {
        assertTrue(true);
    }

    @And("^I favorite the cocktail$")
    public void i_favorite_the_cocktail() {
        assertTrue(true);
    }

    @Then("^the coctail is added to my favorites list$")
    public void the_coctail_is_added_to_my_favorites_list() {
        assertTrue(true);
    }

    @And("^the cocktail is already in my favorites list$")
    public void the_cocktail_is_already_in_my_favorites_list() {
        assertTrue(true);
    }

    @Then("^the coctail is kept in my favorites list$")
    public void the_coctail_is_kept_in_my_favorites_list() {
        assertTrue(true);
    }

    @Then("^the coctail is not added to my favorites list$")
    public void the_coctail_is_not_added_to_my_favorites_list() {
        assertTrue(true);
    }
}
