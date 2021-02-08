package com.ecse428.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
  // TODO Import UserService, write REST endpoints 
  // for adding modifiers & alcohols 
  // TO INVENTORY using UserService methods

  private final UserService userService;

  public UserController (UserService userService){
    this.userService = userService;
  }

  @GetMapping
  public Alcohol getVodka(){
    return userService.getVodka();
  }
  
  @PutMapping
  public void setVodka(Integer amount){
    this.amount = amount;
  }

  @GetMapping
  public Alcohol getGin(){
    return userService.getGin();
  }
  
  @PutMapping
  public void setGin(Integer amount){
    this.amount = amount;
  }

  @GetMapping
  public Alcohol getWhiskey(){
    return userService.getWhiskey();
  }
  
  @PutMapping
  public void setWhiskey(Integer amount){
    this.amount = amount;
  }

  @GetMapping
  public Alcohol getTequila(){
    return userService.getTequila();
  }
  
  @PutMapping
  public void setTequila(Integer amount){
    this.amount = amount;
  }

  @GetMapping
  public Alcohol getRum(){
    return userService.getRum();
  }
  
  @PutMapping
  public void setRum(Integer amount){
    this.amount = amount;
  }

  @GetMapping
  public Alcohol getScoth(){
    return userService.getSotch();
  }
  
  @PutMapping
  public void setScoth(Integer amount){
    this.amount = amount;
  }

  @GetMapping
  public Alcohol getBrandy(){
    return userService.getBrandy();
  }
  
  @PutMapping
  public void setBrandy(Integer amount){
    this.amount = amount;
  }

  @GetMapping
  public Alcohol getBourbon(){
    return userService.getBourbon();
  }
  
  @PutMapping
  public void setBourbon(Integer amount){
    this.amount = amount;
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
