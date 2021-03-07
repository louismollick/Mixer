package com.ecse428.project.acceptance.steps.scenarioSteps.removeAlcohoholType;

import java.util.HashMap;
import java.util.Map;

import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.ecse428.project.model.Alcohol;
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

  @When("I confirm removing it from inventory")
  public void i_confirm_removing_it_my_inventory() {
    // Send request
    final String uri_req = "/api/user/{userId}/alcohol/{alcoholName}";
    Map<String, String> params = new HashMap<String, String>();
    params.put("userId", context.getUser().getId().toString());
    params.put("alcoholName", context.getChosenAlcohol().getName());

    context.setResponse(restTemplate.exchange(uri_req, HttpMethod.DELETE, null, String.class, params));
  }

  @When("I select an alcohol type")
  public void i_select_an_alcohol() {
    ResponseEntity<Alcohol[]> response = restTemplate.getForEntity("/api/alcohol", Alcohol[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    // Select one
    context.setChosenAlcohol(response.getBody()[0]);
    assertNotNull(context.getChosenAlcohol());
  }
}
