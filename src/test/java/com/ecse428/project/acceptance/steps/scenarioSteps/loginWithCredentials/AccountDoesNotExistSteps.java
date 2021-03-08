package com.ecse428.project.acceptance.steps.scenarioSteps.loginWithCredentials;

import com.ecse428.project.acceptance.CucumberConfig;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountDoesNotExistSteps extends CucumberConfig {
  @When("I submit a non-existant email and password pair")
  public void i_submit_a_non_existant_email_and_password_pair() {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("the system will find that there is no account linked to the specified email")
  public void the_system_will_find_that_there_is_no_account_linked_to_the_specified_email() {
    // Write code here that turns the phrase above into concrete actions
  }

}
