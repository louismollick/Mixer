package com.ecse428.project.acceptance.steps.scenarioSteps.loginWithCredentials;

import com.ecse428.project.acceptance.CucumberConfig;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserLoggedInSteps extends CucumberConfig {
  @When("I submit a valid email and password pair")
  public void i_submit_a_valid_email_and_password_pair() {
    // Write code here that turns the phrase above into concrete actions
  }
  
  @Then("the system will confirm the validity of the credentials")
  public void the_system_will_confirm_the_validity_of_the_credentials() {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("will return a valid login token")
  public void will_return_a_valid_login_token() {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("I will be redirected to the main page of Mixer")
  public void i_will_be_redirected_to_the_main_page_of_mixer() {
    // Write code here that turns the phrase above into concrete actions
  }
}
