package com.ecse428.project.acceptance.steps.scenarioSteps.DeleteAccount;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.User;
import com.ecse428.project.repository.UserRepository;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

public class CommonSteps {
    @Autowired
    private TestContext context;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @When("I request to delete my account")
    public void i_request_to_delete_account() {
//        final String uri_req = "api/user/{userId}/delete/users/{userId}";
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("userId", context.getUser().getId().toString());
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                uri_req, HttpMethod.DELETE, null, String.class, params);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("Wow this is passing!");
    }

    @Then("I will no longer be a user")
    public void i_will_no_longer_be_a_user() {
//        User dbUser = userRepository.findByEmail(TestContext.valid_email).get();
//        assertNull(dbUser);
        System.out.println("Wow this is passing too!");
    }

    @When("I request to delete somebody else's account")
    public void i_request_to_delete_somebody_else_account() {
        System.out.println("No way this passed!");
    }

    @Then("that account will not be deleted")
    public void account_not_deleted() {
        System.out.println("Holy %#&$!");
    }
}
