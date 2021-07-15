package com.ecse428.project.util;

import com.ecse428.project.model.Cocktail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CocktailWithInventoryResponse {
    private Set<Cocktail> cocktailsYouCanMake;
    private List<MissingIngredientCocktail> cocktailsMissingIngredients;

    public CocktailWithInventoryResponse() {
        this.cocktailsYouCanMake = new HashSet<Cocktail>();
        this.cocktailsMissingIngredients = new ArrayList<MissingIngredientCocktail>();
    }

    public CocktailWithInventoryResponse(Set<Cocktail> cocktailsYouCanMake,
            List<MissingIngredientCocktail> cocktailsMissingIngredients) {
        this.cocktailsYouCanMake = cocktailsYouCanMake;
        this.cocktailsMissingIngredients = cocktailsMissingIngredients;
    }

    public Set<Cocktail> getCocktailsYouCanMake() {
        return this.cocktailsYouCanMake;
    }

    public void setCocktailsYouCanMake(Set<Cocktail> cocktailsYouCanMake) {
        this.cocktailsYouCanMake = cocktailsYouCanMake;
    }

    public List<MissingIngredientCocktail> getCocktailsMissingIngredients() {
        return this.cocktailsMissingIngredients;
    }

    public void setCocktailsMissingIngredients(List<MissingIngredientCocktail> cocktailsMissingIngredients) {
        this.cocktailsMissingIngredients = cocktailsMissingIngredients;
    }

    public CocktailWithInventoryResponse cocktailsYouCanMake(Set<Cocktail> cocktailsYouCanMake) {
        setCocktailsYouCanMake(cocktailsYouCanMake);
        return this;
    }

    public CocktailWithInventoryResponse cocktailsMissingIngredients(
            List<MissingIngredientCocktail> cocktailsMissingIngredients) {
        setCocktailsMissingIngredients(cocktailsMissingIngredients);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CocktailWithInventoryResponse)) {
            return false;
        }
        CocktailWithInventoryResponse cocktailWithInventoryResponse = (CocktailWithInventoryResponse) o;
        return Objects.equals(cocktailsYouCanMake, cocktailWithInventoryResponse.cocktailsYouCanMake) && Objects
                .equals(cocktailsMissingIngredients, cocktailWithInventoryResponse.cocktailsMissingIngredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktailsYouCanMake, cocktailsMissingIngredients);
    }

    @Override
    public String toString() {
        return "{" + " cocktailsYouCanMake='" + getCocktailsYouCanMake() + "'" + ", cocktailsMissingIngredients='"
                + getCocktailsMissingIngredients() + "'" + "}";
    }

}
