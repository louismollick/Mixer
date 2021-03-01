package com.ecse428.project.acceptance.steps.scenarioSteps.createAccount;

import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonSteps {

  @Autowired
  private TestContext context;

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  UserRepository userRepository;

  @Given("I am somebody with a stable internet connection")
  public void i_am_somebody_with_a_stable_internet_connection() {

  }

  @Given("I am on the Create Account page of the Mixer webaapp")
  public void i_am_on_the_create_account_page_of_the_mixer_webaapp() {

  }

  @When("the system finds that it is a valid email")
  public void the_system_finds_that_it_is_a_valid_email() {

  }

  @Then("I will be redirected to the main page of Mixer")
  public void i_will_be_redirected_to_the_main_page_of_mixer() {

  }

  @When("I enter an email and password")
  public void i_enter_an_email_and_password() {
  }
}
