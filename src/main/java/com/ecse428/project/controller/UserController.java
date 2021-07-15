package com.ecse428.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.Cocktail;
import com.ecse428.project.model.Modifier;
import com.ecse428.project.service.IUserService;
import com.ecse428.project.util.CocktailWithInventoryResponse;

@RestController
@RequestMapping(path = "api/user/{userId}")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("modifier")
    public Set<Modifier> getModifiersInInventory(@PathVariable int userId) {
        return userService.getModifiersInInventory(userId);
    }

    @PutMapping("modifier/{modifierName}")
    public ResponseEntity<String> putModifierInInventory(@PathVariable int userId, @PathVariable String modifierName) {
        return userService.putModifierInInventory(userId, modifierName);
    }

    @GetMapping("alcohol")
    public Set<Alcohol> getAlcoholInInventory(@PathVariable int userId) {
        return userService.getAlcoholInInventory(userId);
    }

    @PutMapping("alcohol/{alcoholName}")
    public ResponseEntity<String> putAlcoholInInventory(@PathVariable int userId, @PathVariable String alcoholName) {
        return userService.putAlcoholInInventory(userId, alcoholName);
    }

    @GetMapping("favouriteCocktail")
    public Set<Cocktail> getFavouriteCocktails(@PathVariable int userId) {
        return userService.getFavouriteCocktail(userId);
    }

    @GetMapping("cocktailsWithInventory")
    public CocktailWithInventoryResponse getCocktailsWithInventory(@PathVariable int userId) {
        return userService.getCocktailsWithInventory(userId);
    }

    @PutMapping("favouriteCocktail/{cocktailName}")
    public ResponseEntity<String> putFavouriteCocktail(@PathVariable int userId, @PathVariable String cocktailName) {
        return userService.putFavouriteCocktail(userId, cocktailName);
    }

    @DeleteMapping("alcohol/{alcoholName}")
    public ResponseEntity<String> deleteAlcoholInInventory(@PathVariable int userId, @PathVariable String alcoholName){
        return userService.deleteAlcoholInInventory(userId, alcoholName);
    }

    @DeleteMapping("modifier/{modifierName}")
    public ResponseEntity<String> deleteModifierInInventory(@PathVariable int userId, @PathVariable String modifierName){
        return userService.deleteModifierInInventory(userId, modifierName);
    }

    @DeleteMapping("favouriteCocktail/{cocktailName}")
    public ResponseEntity<String> deleteFavouriteCocktail(@PathVariable int userId, @PathVariable String cocktailName){
        return userService.deleteFavouriteCocktail(userId, cocktailName);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteAccount(@PathVariable int userId){
        return userService.deleteAccount(userId);
    }
}
