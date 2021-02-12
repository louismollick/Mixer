package com.ecse428.project.service;

import java.util.List;

import com.ecse428.project.model.Cocktail;
import com.ecse428.project.repository.CocktailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICocktailService implements CocktailService{

    private final CocktailRepository cocktailRepository;

    @Autowired
   public ICocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public List<Cocktail> getCocktail(){
        return cocktailRepository.findAll();
    }
}
