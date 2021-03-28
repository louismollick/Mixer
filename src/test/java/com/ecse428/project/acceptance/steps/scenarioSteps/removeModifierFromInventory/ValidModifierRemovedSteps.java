package com.ecse428.project.acceptance.steps.scenarioSteps.removeModifierFromInventory;

import com.ecse428.project.acceptance.CucumberConfig;
import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.model.Modifier;
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

import static org.junit.jupiter.api.Assertions.*;


public class ValidModifierRemovedSteps extends CucumberConfig  {

	@Autowired
	private TestContext context;

	@Autowired
	TestRestTemplate restTemplate;

	@Autowired
	UserRepository userRepository;

    @When("The modifier exists in my inventory")
    public void the_modifier_exists_in_my_inventory() {
        
        User user = context.getUser();
        user.addModifierToInventory(context.getChosenModifier());
        userRepository.save(user);

        context.setUser(userRepository.findByEmail(TestContext.valid_email).get());
        assertTrue(context.getUser().getModifiersInInventory().contains(context.getChosenModifier()));
    }

    @Then("The system will remove the modifier from my inventory")
    public void the_system_will_remove_the_modifier_from_my_inventory() {
        assertEquals(HttpStatus.OK, context.getResponse().getStatusCode());
        final String uri_req = "/api/user/{userId}/modifier/";
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", context.getUser().getId().toString());

        ResponseEntity<Modifier[]> response = restTemplate.exchange(uri_req, HttpMethod.GET, null, Modifier[].class,
                params);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Modifier> list = Arrays.asList(response.getBody());
        assertEquals(0, list.size());
        assertFalse(list.contains(context.getChosenModifier()));
    }
}