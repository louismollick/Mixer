package com.ecse428.project.controller;

import java.util.List;

import com.ecse428.project.model.Cocktail;
import com.ecse428.project.service.CocktailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/cocktail")
public class CocktailController {
    private final CocktailService cocktailService;

    @Autowired
    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping
    public List<Cocktail> getCocktails(){
        return cocktailService.getCocktail();
    }
}
