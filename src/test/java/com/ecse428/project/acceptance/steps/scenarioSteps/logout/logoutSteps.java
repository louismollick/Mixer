package com.ecse428.project.acceptance.steps.scenarioSteps.logout;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.User;
import com.ecse428.project.repository.UserRepository;
import io.cucumber.java.en.And;
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

public class logoutSteps {
    @Autowired
    private TestContext context;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @When("I press logout button")
    public void I_press_logout_button() {
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

    @Then("I am no longer authenticated")
    public void I_am_no_longer_authenticated() {
//        User dbUser = userRepository.findByEmail(TestContext.valid_email).get();
//        assertNull(dbUser);
        System.out.println("");
    }

    @And("I am redirected to the login page")
    public void I_am_redirected_to_the_login_page() {
        System.out.println("");
    }

    @Then("I am still authenticated")
    public void I_am_still_authenticatedr() {
//        User dbUser = userRepository.findByEmail(TestContext.valid_email).get();
//        assertNull(dbUser);
        System.out.println("");
    }

    @And("I am still on the logout page")
    public void I_am_still_on_the_logout_page() {
        System.out.println("");
    }
}
