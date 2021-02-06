package com.ecse428.project.acceptance.steps.commonSteps;

import com.ecse428.project.acceptance.CucumberConfig;

import io.cucumber.java.en.Given;

public class UserLoggedInSteps extends CucumberConfig {
  @Given("I am a user")
  public void i_am_a_user() {
    // Create user and persist, basically make sure one exists that you can get in future steps
    throw new io.cucumber.java.PendingException();
  }

  @Given("I am logged in to Mixer")
  public void i_am_logged_in_to_mixer() {
    // Get the user and make them logged in
    throw new io.cucumber.java.PendingException();
  }
}
