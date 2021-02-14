package com.ecse428.project.acceptance.steps.scenarioSteps.addModifierToInventory;

import com.ecse428.project.acceptance.CucumberConfig;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.ecse428.project.acceptance.steps.commonSteps.UserLoggedInSteps;
import com.ecse428.project.model.Modifier;
import com.ecse428.project.model.User;
import com.ecse428.project.repository.UserRepository;
import com.ecse428.project.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InvalidModifierAddedSteps extends CucumberConfig {

  @Autowired
  TestRestTemplate restTemplate;
  private IUserService service;

  @Autowired
  UserRepository userRepository;

  ResponseEntity<Void> response;
  private Modifier chosen;
  private User user;
  String errorMessage = "";

  @When("I select a modifier")
  public void i_select_a_modifier() {
    // Get the user
    user = userRepository.findByUsername(UserLoggedInSteps.userName).get();
    // Creating invalid modifier
    chosen = new Modifier("Invalid Modifier");
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
  
    //assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Then("the system will notify me that the modifier is invalid")
  public void the_system_will_notify_me_that_the_modifier_is_invalid() {
    //Not sure how to confirm the modifier is invalid
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    //assertEquals("Modifier not found with name Invalid Modifier.", errorMessage);
    System.out.println("The input modifier is invalid");
  }

  @Then("the modifier will not be in my inventory")
  public void the_modifier_will_not_be_in_my_inventory() {
    //service.putModifierInInventory(user.getId().toString(), chosen)
    final String uri_req = "/api/user/{userId}/modifier/";
    Map<String, String> params = new HashMap<String, String>();
    params.put("userId", user.getId().toString());

    ResponseEntity<Modifier[]> response = restTemplate.exchange(uri_req, HttpMethod.GET, null, Modifier[].class, params);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertFalse(Arrays.asList(response.getBody()).contains(chosen));




}

}