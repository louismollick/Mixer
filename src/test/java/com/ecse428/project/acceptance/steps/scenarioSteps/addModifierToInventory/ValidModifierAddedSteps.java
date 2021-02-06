package com.ecse428.project.acceptance.steps.scenarioSteps.addModifierToInventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ecse428.project.model.Modifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ValidModifierAddedSteps {

  @LocalServerPort
  private static int port;

  @Autowired
  TestRestTemplate restTemplate;

  private Modifier chosen;

  @When("I select a modifier")
  public void i_select_a_modifier() {
    // Query all modifiers
    ResponseEntity<Modifier[]> response = restTemplate.getForEntity("/api/modifier", Modifier[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    // Select one
    chosen = response.getBody()[0];
    assertNotNull(chosen);
    System.out.println(chosen);
  }

  @When("I add confirm adding it to my inventory")
  public void i_add_confirm_adding_it_to_my_inventory() {
    // Get user
    // Add modifier to user's inventory
    // Save user to database
    throw new io.cucumber.java.PendingException();
  }

  @Then("the system will add the modifier to my inventory")
  public void the_system_will_add_the_modifier_to_my_inventory() {
    // Assert that the modifier is in the user's inventory either using the
    // UserRepository
    // or by querying it with the restTemplate
    throw new io.cucumber.java.PendingException();
  }
}
