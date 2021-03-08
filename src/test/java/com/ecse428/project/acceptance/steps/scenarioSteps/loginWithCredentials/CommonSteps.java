package com.ecse428.project.acceptance.steps.scenarioSteps.loginWithCredentials;

import com.ecse428.project.acceptance.CucumberConfig;

import io.cucumber.java.en.Then;

public class CommonSteps extends CucumberConfig {
  @Then("a new login token will not be created")
  public void a_new_login_token_will_not_be_created() {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("the system will return the error {string}")
  public void the_system_will_return_the_error(String string) {
    // Write code here that turns the phrase above into concrete actions
  }
}
