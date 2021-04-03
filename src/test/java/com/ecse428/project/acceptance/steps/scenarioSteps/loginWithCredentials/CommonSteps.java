package com.ecse428.project.acceptance.steps.scenarioSteps.loginWithCredentials;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.ecse428.project.acceptance.CucumberConfig;
import com.ecse428.project.acceptance.TestContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonSteps extends CucumberConfig {
  @Autowired
  private TestContext context;

  @Autowired
  TestRestTemplate restTemplate;

  @Then("a new login token will not be created")
  public void a_new_login_token_will_not_be_created() {
    assertFalse(context.getResponse().getBody().contains("Bearer")); // the token format should be missing
    assertNull(context.getResponse().getHeaders().get("Authorization"));
  }

  @When("I submit a login request")
  public void submit_login() {
    HttpEntity<LoginCreds> userRequest = new HttpEntity<>(context.getLoginCreds());
    ResponseEntity<String> response = restTemplate.exchange("/login", HttpMethod.POST, userRequest, String.class);
    context.setResponse(response);
  }
}
