package com.ecse428.project.acceptance.steps.scenarioSteps.removeModifier;

import java.util.HashMap;
import java.util.Map;

import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;

import io.cucumber.java.en.When;

public class CommonSteps {

  @Autowired
  private TestContext context;

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  UserRepository userRepository;

  @When("I confirm removing the modifier from inventory")
  public void i_confirm_removing_my_inventory() {
    // Send request
    final String uri_req = "/api/user/{userId}/modifier/{modifierName}";
    Map<String, String> params = new HashMap<String, String>();
    params.put("userId", context.getUser().getId().toString());
    params.put("modifierName", context.getChosenModifier().getName());

    context.setResponse(restTemplate.exchange(uri_req, HttpMethod.DELETE, null, String.class, params));
  }
}
