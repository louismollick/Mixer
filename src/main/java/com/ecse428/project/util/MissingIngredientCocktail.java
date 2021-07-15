package com.ecse428.project.util;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.ecse428.project.model.Cocktail;
import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.Modifier;

public class MissingIngredientCocktail {
    private Cocktail cocktail;
    private Set<Alcohol> missingAlcohols;
    private Set<Modifier> missingModifiers;

    public MissingIngredientCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
        this.missingAlcohols = new HashSet<Alcohol>();
        this.missingModifiers = new HashSet<Modifier>();
    }

    public MissingIngredientCocktail(Cocktail cocktail, Set<Alcohol> missingAlcohols, Set<Modifier> missingModifiers) {
        this.cocktail = cocktail;
        this.missingAlcohols = missingAlcohols;
        this.missingModifiers = missingModifiers;
    }

    public Cocktail getCocktail() {
        return this.cocktail;
    }

    public void setCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
    }

    public Set<Alcohol> getMissingAlcohols() {
        return this.missingAlcohols;
    }

    public void setMissingAlcohols(Set<Alcohol> missingAlcohols) {
        this.missingAlcohols = missingAlcohols;
    }

    public Set<Modifier> getMissingModifiers() {
        return this.missingModifiers;
    }

    public void setMissingModifiers(Set<Modifier> missingModifiers) {
        this.missingModifiers = missingModifiers;
    }

    public MissingIngredientCocktail cocktail(Cocktail cocktail) {
        setCocktail(cocktail);
        return this;
    }

    public MissingIngredientCocktail missingAlcohols(Set<Alcohol> missingAlcohols) {
        setMissingAlcohols(missingAlcohols);
        return this;
    }

    public MissingIngredientCocktail missingModifiers(Set<Modifier> missingModifiers) {
        setMissingModifiers(missingModifiers);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MissingIngredientCocktail)) {
            return false;
        }
        MissingIngredientCocktail missingIngredientCocktail = (MissingIngredientCocktail) o;
        return Objects.equals(cocktail, missingIngredientCocktail.cocktail) && Objects.equals(missingAlcohols, missingIngredientCocktail.missingAlcohols) && Objects.equals(missingModifiers, missingIngredientCocktail.missingModifiers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktail, missingAlcohols, missingModifiers);
    }

    @Override
    public String toString() {
        return "{" +
            " cocktail='" + getCocktail() + "'" +
            ", missingAlcohols='" + getMissingAlcohols() + "'" +
            ", missingModifiers='" + getMissingModifiers() + "'" +
            "}";
    }

}
