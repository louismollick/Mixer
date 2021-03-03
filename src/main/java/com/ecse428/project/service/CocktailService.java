package com.ecse428.project.service;

import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.Cocktail;
import com.ecse428.project.model.Modifier;

import java.util.List;
import java.util.Optional;

public interface CocktailService {
    public List<Cocktail> getCocktail();

    public List<Cocktail> getCocktailByNameContains(String cocktailName);

    public List<Cocktail> getCocktailByParams(String cName, Alcohol alcohol, Modifier modifier,
                                              Cocktail.TasteType tasteType, Cocktail.StrengthType strengthType,
                                              Cocktail.ServingSize servingSize);

}
