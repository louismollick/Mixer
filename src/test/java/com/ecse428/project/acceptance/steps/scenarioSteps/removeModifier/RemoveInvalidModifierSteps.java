package com.ecse428.project.acceptance.steps.scenarioSteps.removeModifier;

import com.ecse428.project.acceptance.CucumberConfig;
import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.repository.UserRepository;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class RemoveInvalidModifierSteps extends CucumberConfig {
  @Autowired
  private TestContext context;

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  UserRepository userRepository;

  @Then("the system will display an error about the modifier")
  public void the_system_will_display_an_error() {
    assertEquals(HttpStatus.NOT_FOUND, context.getResponse().getStatusCode());
    assertTrue(context.getResponse().getBody().toString()
        .contains("Modifier not found with name " + TestContext.invalid_name + "."));
  }

  @Then("no modifier will be removed from my inventory")
  public void no_modifier_will_be_removed_from_my_inventory() {
    assertTrue(context.getUser().getModifiersInInventory()
        .equals(userRepository.findByEmail(TestContext.valid_email).get().getModifiersInInventory()));
  }
}
