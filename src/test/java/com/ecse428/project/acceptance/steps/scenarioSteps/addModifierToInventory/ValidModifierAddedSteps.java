package com.ecse428.project.acceptance.steps.scenarioSteps.addModifierToInventory;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.ecse428.project.acceptance.steps.commonSteps.UserLoggedInSteps;
import com.ecse428.project.model.Modifier;
import com.ecse428.project.model.User;
import com.ecse428.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ValidModifierAddedSteps {

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  UserRepository userRepository;

  private Modifier chosen;
  private User user;

  @When("I select a modifier")
  public void i_select_a_modifier() {
    // Get the user
    user = userRepository.findByUsername(UserLoggedInSteps.userName).get();

    // Query all modifiers
    ResponseEntity<Modifier[]> response = restTemplate.getForEntity("/api/modifier", Modifier[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    // Select one
    chosen = response.getBody()[0];
    assertNotNull(chosen);
    // Should probably assert a known modifier
    System.out.println(chosen);
  }

  @When("I confirm adding it to my inventory")
  public void i_add_confirm_adding_it_to_my_inventory() {
    // Send request
    final String uri_req = "/api/user/{userId}/modifier/{modifierName}";
    Map<String, String> params = new HashMap<String, String>();
    params.put("userId", user.getId().toString());
    params.put("modifierName", chosen.getName());

    ResponseEntity<Void> response = restTemplate.exchange(uri_req, HttpMethod.PUT, null, Void.class, params);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Then("the system will add the modifier to my inventory")
  public void the_system_will_add_the_modifier_to_my_inventory() {
    final String uri_req = "/api/user/{userId}/modifier/";
    Map<String, String> params = new HashMap<String, String>();
    params.put("userId", user.getId().toString());

    ResponseEntity<Modifier[]> response = restTemplate.exchange(uri_req, HttpMethod.GET, null, Modifier[].class, params);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    assertTrue(Arrays.asList(response.getBody()).contains(chosen));
  }
}
