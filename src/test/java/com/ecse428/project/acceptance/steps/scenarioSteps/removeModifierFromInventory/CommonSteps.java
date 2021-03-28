package com.ecse428.project.acceptance.steps.scenarioSteps.removeModifierFromInventory;

import java.util.HashMap;
import java.util.Map;

import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ecse428.project.model.Modifier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.cucumber.java.en.When;

public class CommonSteps {

  @Autowired
  private TestContext context;

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  UserRepository userRepository;

  @When("I select a modifier")
  public void i_select_a_modifier() {
	  
    ResponseEntity<Modifier[]> response = restTemplate.getForEntity("/api/modifier", Modifier[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    context.setChosenModifier(response.getBody()[0]);
    assertNotNull(context.getChosenModifier());
  }
  
  @When("I confirm removing it from my inventory")
  public void i_confirm_removing_it_from_my_inventory() {
	  
    final String uri_req = "/api/user/{userId}/modifier/{modifierName}";
    Map<String, String> params = new HashMap<String, String>();
    params.put("userId", context.getUser().getId().toString());
    params.put("modifierName", context.getChosenModifier().getName());
    context.setResponse(restTemplate.exchange(uri_req, HttpMethod.DELETE, null, String.class, params));
  }

}