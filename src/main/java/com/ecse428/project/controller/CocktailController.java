package com.ecse428.project.controller;

import java.util.List;
import java.util.ArrayList;

import com.ecse428.project.model.Cocktail;
import com.ecse428.project.service.CocktailService;
import com.ecse428.project.service.ICocktailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/cocktail")
public class CocktailController {
    private final CocktailService cocktailService;

    @Autowired
    public CocktailController(ICocktailService cocktailService){
        this.cocktailService = cocktailService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cocktail> getCocktail() {
        return cocktailService.getCocktail();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cocktail> getCocktailByParams(@RequestParam(required = false) String cName, @RequestParam(required = false) ArrayList<String> alcohol, @RequestParam(required = false) ArrayList<String> modifier,
                                              @RequestParam(required = false) ArrayList<String> tasteType, @RequestParam(required = false) String strengthType,
                                              @RequestParam(required = false) String servingSize) {
        return cocktailService.getCocktailByParams(cName, alcohol, modifier, tasteType, strengthType, servingSize);
    }
}
