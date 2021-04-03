package com.ecse428.project.acceptance.steps.scenarioSteps.loginWithCredentials;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import com.ecse428.project.acceptance.CucumberConfig;
import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.model.Alcohol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserLoggedInSteps extends CucumberConfig {
  @Autowired
  private TestContext context;

  @Autowired
  TestRestTemplate restTemplate;

  @When("I submit a valid email and password pair")
  public void i_submit_a_valid_email_and_password_pair() {
    context.setLoginCreds(new LoginCreds(TestContext.valid_email, TestContext.valid_password));
  }

  @Then("the system will confirm the validity of the credentials")
  public void the_system_will_confirm_the_validity_of_the_credentials() {
    assertEquals(HttpStatus.OK, context.getResponse().getStatusCode());
  }

  @Then("will return a valid login token")
  public void will_return_a_valid_login_token() {
    assertTrue(context.getResponse().getBody().contains("Bearer")); // the token format
    assertTrue(context.getResponse().getBody().contains("email"));
    assertTrue(context.getResponse().getBody().contains("userId")); // the two other return values

    // Verify that we previously could not access protected routes
    ResponseEntity<String> response = restTemplate.exchange("/api/alcohol", HttpMethod.GET, null, String.class);
    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

    // Now try with token
    restTemplate.getRestTemplate().setInterceptors(Collections.singletonList((request, body, execution) -> {
      request.getHeaders().add("Authorization", context.getResponse().getHeaders().get("Authorization").get(0));
      return execution.execute(request, body);
    }));
    ResponseEntity<Alcohol[]> response2 = restTemplate.getForEntity("/api/alcohol", Alcohol[].class);
    assertEquals(HttpStatus.OK, response2.getStatusCode());
  }
}
