package com.ecse428.project.acceptance.steps.scenarioSteps.loginWithCredentials;

import com.ecse428.project.acceptance.CucumberConfig;
import com.ecse428.project.acceptance.TestContext;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.When;

public class EmptyEmailOrPasswordSteps extends CucumberConfig {
  @Autowired
  private TestContext context;
  
  @When("I enter an empty email")
  public void i_enter_an_empty_email() {
    context.setLoginCreds(new LoginCreds("", TestContext.valid_password));
  }

  @When("I enter an empty password")
  public void i_enter_an_empty_password() {
    context.setLoginCreds(new LoginCreds(TestContext.valid_email, ""));
  }
}
