package com.ecse428.project.service;

import com.ecse428.project.model.Cocktail;

import java.util.List;

public interface CocktailService {
    public List<Cocktail> getCocktail();

    public List<Cocktail> getCocktailByNameContains(String cocktailName);

    public List<Cocktail> getCocktailByParams(String cName, List<String> alcohol, List<String> modifier,
                                              List<String> tasteType, String strengthType,
                                              String servingSize);
}
