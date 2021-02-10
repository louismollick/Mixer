package com.ecse428.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "api/user/{userId}")
public class UserController {

	  @Autowired
	  private final UserService userService;

	  public UserController (UserService userService){
	    this.userService = userService;
	  }
	  
	  @GetMapping("modifier")
	  public Modifier getModifierInInventory(@PathVariable int userId){
	    return userService.getModifierInInventory(userId);
	  }
	  
	  @PutMapping("modifier/{modifierName}")
	  public void addModifierToInventory(@PathVariable int userId, @PathVariable String modifierName){
	    return userService.putModifierInInventory(userId, modifierName);
	  }
}
