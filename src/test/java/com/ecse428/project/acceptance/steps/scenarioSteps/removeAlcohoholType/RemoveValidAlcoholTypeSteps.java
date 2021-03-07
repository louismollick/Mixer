package com.ecse428.project.acceptance.steps.scenarioSteps.removeAlcohoholType;
import com.ecse428.project.acceptance.CucumberConfig;
import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.User;
import com.ecse428.project.repository.UserRepository;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


public class RemoveValidAlcoholTypeSteps extends CucumberConfig  {
    
    @Autowired
    private TestContext context;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    /*
    @And("I have an alcohol in my inventory")
    public void i_have_alcohol_in_inventory() {
        final String uri_req = "/api/user/{userId}/alcohol/";
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", context.getUser().getId().toString());

        restTemplate.exchange(uri_req, HttpMethod.GET, null, Alcohol[].class, params);

        ResponseEntity<Alcohol[]> response = restTemplate.getForEntity("/api/alcohol", Alcohol[].class);
        context.setChosenAlcohol(response.getBody()[0]);
    }

    @And("I have a modifier in my inventory")
    public void i_have_modifier_in_inventory() {
        final String uri_req = "/api/user/{userId}/modifier/";
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", context.getUser().getId().toString());

        restTemplate.exchange(uri_req, HttpMethod.GET, null, Modifier[].class, params);

        ResponseEntity<Modifier[]> response = restTemplate.getForEntity("/api/modifier", Modifier[].class);
        context.setChosenModifier(response.getBody()[0]);
    }

    @And("I have items in my inventory")
    public void i_have_items_in_inventory() {
        i_have_alcohol_in_inventory();
        i_have_modifier_in_inventory();
    }
    */


    @When("the alcohol type exists in inventory")
    public void the_alcohol_exists_in_inventory() {
        // Put in inventory
        User user = context.getUser();
        user.addAlcoholToInventory(context.getChosenAlcohol());
        userRepository.save(user);

        context.setUser(userRepository.findByEmail(TestContext.valid_email).get());
        assertTrue(context.getUser().getAlcoholInInventory().contains(context.getChosenAlcohol()));
    }

    @Then("the system will remove the alcohol type from my inventory")
    public void the_system_will_remove_the_aclohol_type_from_my_inventory() {
        assertEquals(HttpStatus.OK, context.getResponse().getStatusCode());
        final String uri_req = "/api/user/{userId}/alcohol/";
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", context.getUser().getId().toString());

        ResponseEntity<Alcohol[]> response = restTemplate.exchange(uri_req, HttpMethod.GET, null, Alcohol[].class,
                params);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Alcohol> list = Arrays.asList(response.getBody());
        assertEquals(0, list.size());
        assertFalse(list.contains(context.getChosenAlcohol()));
    }

}
