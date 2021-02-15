package com.ecse428.project.acceptance.steps.scenarioSteps.addModifierToInventory;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.ecse428.project.acceptance.CucumberConfig;
import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ValidCreateAccountSteps extends CucumberConfig {

  @Autowired
  private TestContext context;

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  UserRepository userRepository;

  @When("I enter an email and password")
  public void i_enter_an_email_and_password() {
  }

  @Then("the system will create my account with email and password")
  public void the_system_will_create_my_account_with_email_and_password() {
  }
}
