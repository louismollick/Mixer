package com.ecse428.project.service;

import java.util.List;

import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.Cocktail;
import com.ecse428.project.model.Modifier;
import com.ecse428.project.repository.AlcoholRepository;
import com.ecse428.project.repository.CocktailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ICocktailService implements CocktailService{

    @Autowired
    private final CocktailRepository cocktailRepository;

    @Autowired
    private final AlcoholRepository alcoholRepository;

    @Autowired
    public ICocktailService(CocktailRepository cocktailRepository, AlcoholRepository alcoholRepository) {
        this.cocktailRepository = cocktailRepository;
        this.alcoholRepository = alcoholRepository;
    }

    @Override
    public List<Cocktail> getCocktail(){
        return cocktailRepository.findAll();
    }

    @Override
    public List<Cocktail> getCocktailByNameContains(String cocktailName) {
        List<Cocktail> cocktails = cocktailRepository.findByNameContaining(cocktailName);

        if (cocktails.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cocktail(s) not found containing key name " + cocktailName + ".");
        }

        return cocktails;
    }

    @Override
    public List<Cocktail> getCocktailByParams(String cName, Alcohol alcohol, Modifier modifier, Cocktail.TasteType tasteType,
                                              Cocktail.StrengthType strengthType, Cocktail.ServingSize servingSize) {

        List<Cocktail> cocktails = cocktailRepository.findByParams(cName, alcohol, modifier, tasteType, strengthType, servingSize);

        if (cocktails.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cocktail(s) not found with taste preference");
        }

        return cocktails;
    }
}
