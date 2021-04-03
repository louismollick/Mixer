package com.ecse428.project.acceptance.steps.scenarioSteps.loginWithCredentials;

import static org.junit.Assert.assertEquals;

import com.ecse428.project.acceptance.CucumberConfig;
import com.ecse428.project.acceptance.TestContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountDoesNotExistSteps extends CucumberConfig {
  @Autowired
  private TestContext context;
  
  @When("I submit a non-existant email and password pair")
  public void i_submit_a_non_existant_email_and_password_pair() {
    // non existant email
    context.setLoginCreds(new LoginCreds(TestContext.invalid_name, TestContext.valid_password));
  }

  @Then("the system will find that there is no account linked to the specified email")
  public void the_system_will_find_that_there_is_no_account_linked_to_the_specified_email() {
    assertEquals(HttpStatus.BAD_REQUEST, context.getResponse().getStatusCode());
  }

}
