package com.ecse428.project.acceptance.steps.scenarioSteps.loginWithCredentials;

import static org.junit.Assert.assertEquals;

import com.ecse428.project.acceptance.CucumberConfig;
import com.ecse428.project.acceptance.TestContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class InvalidCredentialsSteps extends CucumberConfig {
  @Autowired
  private TestContext context;

  @When("I submit an incorrect email and password pair")
  public void i_submit_an_incorrect_email_and_password_pair() {
    // valid email but incorrect password
    context.setLoginCreds(new LoginCreds(TestContext.valid_email, TestContext.invalid_password));
  }

  @Then("the system will find that the password is incorrect")
  public void the_system_will_find_that_the_password_is_incorrect() {
    assertEquals(HttpStatus.BAD_REQUEST, context.getResponse().getStatusCode());
  }
}
