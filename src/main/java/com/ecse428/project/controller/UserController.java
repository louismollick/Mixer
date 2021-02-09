package com.ecse428.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/user/{userId}")
public class UserController {
  // TODO Import UserService, write REST endpoints 
  // for adding modifiers & alcohols 
  // TO INVENTORY using UserService methods

  @Autowired
  private final UserService userService;

  public UserController (UserService userService){
    this.userService = userService;
  }


  @GetMapping("alcohol")
  public Alcohol getAlcoholInInventory(@PathVariable int userId){
    return userService.getAlcoholInInventory(userId);
  }
  
  @PutMapping("alcohol/{alcoholName}")
  public void addAlcoholToInventory(@PathVariable int userId, @PathVariable String alcoholName){
    return userService.putAlcoholInInventory(userId, alcoholName);
  }


  //vodka
  //gin
  //whiskey
  //tequila
  //rum
  //scotch
  //brandy
  //bourbon

}
