package com.ecse428.project.acceptance.steps.scenarioSteps.removeAlcohoholType;
import com.ecse428.project.acceptance.CucumberConfig;
import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.model.User;
import com.ecse428.project.repository.UserRepository;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


public class RemoveInvalidAlcoholTypeSteps extends CucumberConfig  {
    
    @Autowired
    private TestContext context;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;



    @When("the alcohol type does not exist in inventory")
    public void the_alcohol_type_does_not_exist_in_inventory() {
        // Put in inventory
        User user = context.getUser();
        user.addAlcoholToInventory(context.getChosenAlcohol());
        userRepository.save(user);

        context.setUser(userRepository.findByEmail(TestContext.valid_email).get());
        assertFalse(context.getUser().getAlcoholInInventory().contains(context.getChosenAlcohol()));
    }

    @Then("the system will display and error")
    public void the_system_will_display_an_error() {
      assertEquals(HttpStatus.NOT_FOUND, context.getResponse().getStatusCode());
  
      assertTrue(context.getResponse().getBody().toString().contains("Alcohol not found with name " + TestContext.invalid_name + "."));
    }
}
