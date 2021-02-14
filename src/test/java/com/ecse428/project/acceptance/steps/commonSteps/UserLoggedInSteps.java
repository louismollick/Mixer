package com.ecse428.project.acceptance.steps.commonSteps;

import com.ecse428.project.acceptance.CucumberConfig;
import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.model.User;
import com.ecse428.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;

public class UserLoggedInSteps extends CucumberConfig {

  @Autowired
  private TestContext context;

  public static String userName = "Jimmy";

  @Autowired
  UserRepository userRepository;

  @Given("I am a user")
  public void i_am_a_user() {
    // Create user and persist, basically make sure one exists that you can get in
    // future steps
    if (!userRepository.existsByUsername(userName))
      userRepository.save(new User(userName));

    // Get the user
    context.setUser(userRepository.findByUsername(UserLoggedInSteps.userName).get());
  }

  @Given("I am logged in to Mixer")
  public void i_am_logged_in_to_mixer() {
    // Get the user and make them logged in
    // throw new io.cucumber.java.PendingException();
  }
}
