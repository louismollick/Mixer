package com.ecse428.project.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cocktail")
public class Cocktail {

    @Id
    @Column(name = "cocktail_name")
    private String name;

    @ManyToMany
    private List<Modifier> modifiers;

    @ManyToMany
    private List<Alcohol> alcohols;

    public Cocktail(){ }

    public void addAlcohol(Alcohol alcohol) { alcohols.add(alcohol); }

    public void addModifier(Modifier modifier) { modifiers.add(modifier); }

    public void setAlcohols(List<Alcohol> alcohols) {
        this.alcohols = alcohols;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModifiers(List<Modifier> modifiers) { this.modifiers = modifiers; }

    public String getName() {
        return name;
    }

    public List<Alcohol> getAlcohols() {
        return alcohols;
    }

    public List<Modifier> getModifiers() {
        return modifiers;
    }

    public Cocktail(String name) {
        this.name = name;
    }

    public Cocktail(String name, List<Modifier> modifiers) {
        this.name = name;
        this.modifiers = modifiers;
    }

    public Cocktail(String name, List<Modifier> modifiers, List<Alcohol> alcohols) {
        this.name = name;
        this.modifiers = modifiers;
        this.alcohols = alcohols;
    }
}
