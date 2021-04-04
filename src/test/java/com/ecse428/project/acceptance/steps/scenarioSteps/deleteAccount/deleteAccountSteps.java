package com.ecse428.project.acceptance.steps.scenarioSteps.deleteAccount;

import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.model.User;
import com.ecse428.project.repository.UserRepository;
import com.ecse428.project.service.UserService;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class deleteAccountSteps {
    @Autowired
    private TestContext context;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @When("I request to delete my account")
    public void i_request_to_delete_account() {
        final String uri_req = "/api/user/{userId}/delete";
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", context.getUser().getId().toString());
        ResponseEntity<String> response = restTemplate.exchange(uri_req, HttpMethod.DELETE, null, String.class, params);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        context.setResponse(response);
    }

    @Then("I will no longer be a user")
    public void i_will_no_longer_be_a_user() {
        assertTrue(userRepository.findByEmail(TestContext.valid_email).isEmpty());
    }

    @When("I request to delete somebody else's account")
    public void i_request_to_delete_somebody_else_account() {
        // Create new user and get id
        var otheremail = "billybob@yahoo.com";
        User newUser = new User(otheremail, "12345678");
        userService.postSignup(newUser);
        var otheruser = userRepository.findByEmail(otheremail).get();
        var otherid = otheruser.getId();
        context.setUser(otheruser);

        // Request to delete other person's account -- should give 403 Forbiddens
        final String uri_req = "/api/user/{userId}/delete";
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", String.valueOf(otherid));
        ResponseEntity<String> response = restTemplate.exchange(uri_req, HttpMethod.DELETE, null, String.class, params);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

        context.setResponse(response);
    }

    @Then("that account will not be deleted")
    public void account_not_deleted() {
        // Verify that the other user in DB is the same as user in TestContext
        var otheruser = context.getUser();
        assertTrue(userRepository.findByEmail(otheruser.getEmail()).get().getId().equals(otheruser.getId()));
    }
}
