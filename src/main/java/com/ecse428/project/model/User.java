package com.ecse428.project.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(name = "user_id")
    private Long id;

    @Column(unique=true)
    private String username;
    
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_alcohols", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "alcohol_name"))
    private Set<Alcohol> alcoholInInventory;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_modifiers", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "modifier_name"))
    private Set<Modifier> modifiersInInventory;


    public User() {
    }

    public User(String username) {
        this.username = username;
        this.alcoholInInventory = new HashSet<Alcohol>();
        this.modifiersInInventory = new HashSet<Modifier>();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.alcoholInInventory = new HashSet<Alcohol>();
        this.modifiersInInventory = new HashSet<Modifier>();
    }

    public User(Long id, String username, String password, Set<Alcohol> alcoholInInventory, Set<Modifier> modifiersInInventory) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.alcoholInInventory = alcoholInInventory;
        this.modifiersInInventory = modifiersInInventory;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Alcohol> getAlcoholInInventory() {
        return this.alcoholInInventory;
    }

    public void setAlcoholInInventory(Set<Alcohol> alcoholInInventory) {
        this.alcoholInInventory = alcoholInInventory;
    }

    public void addAlcoholToInventory(Alcohol chosenAlcohol) {
        this.alcoholInInventory.add(chosenAlcohol);
	}

    public Set<Modifier> getModifiersInInventory() {
        return this.modifiersInInventory;
    }

    public void setModifiersInInventory(Set<Modifier> modifiersInInventory) {
        this.modifiersInInventory = modifiersInInventory;
    }

    public void addModifierToInventory(Modifier chosenModifier) {
        this.modifiersInInventory.add(chosenModifier);
	}

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username)
                && Objects.equals(alcoholInInventory, user.alcoholInInventory)
                && Objects.equals(modifiersInInventory, user.modifiersInInventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, alcoholInInventory, modifiersInInventory);
    }

}
