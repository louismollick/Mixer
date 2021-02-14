package com.ecse428.project.acceptance;

import com.ecse428.project.model.Modifier;
import com.ecse428.project.model.User;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.cucumber.spring.ScenarioScope;

@Component
@ScenarioScope
public class TestContext {

  public static String invalid_name = "Invalid Modifier";
  private ResponseEntity<String> response;
  private Modifier chosen;
  private User user;

  public ResponseEntity<String> getResponse() {
    return this.response;
  }

  public void setResponse(ResponseEntity<String> response) {
    this.response = response;
  }

  public Modifier getChosen() {
    return this.chosen;
  }

  public void setChosen(Modifier chosen) {
    this.chosen = chosen;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

 }