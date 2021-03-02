package com.ecse428.project.controller;

import com.ecse428.project.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class RootController {

  @Autowired
  private final UserService userService;

  public RootController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/signup")
  public ResponseEntity<String> signupPost(@PathVariable String username, @PathVariable String password) {
    return userService.postSignup(username, password);
  }
}
