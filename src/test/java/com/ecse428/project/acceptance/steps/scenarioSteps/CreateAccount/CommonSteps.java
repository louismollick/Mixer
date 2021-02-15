package com.ecse428.project.acceptance.steps.scenarioSteps.createAccount;

import java.util.HashMap;
import java.util.Map;

import com.ecse428.project.acceptance.TestContext;
import com.ecse428.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;

import io.cucumber.java.en.When;

public class CommonSteps {

  @Autowired
  private TestContext context;

  @Autowired
  TestRestTemplate restTemplate;

  @Autowired
  UserRepository userRepository;
}
