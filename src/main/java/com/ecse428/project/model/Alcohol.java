package com.ecse428.project.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Alcohol {

    @Id
    @Column(name = "alcohol_name")
    private String name;

    public Alcohol() {
    }

    public Alcohol(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "{" + " name='" + getName() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Alcohol)) {
            return false;
        }
        Alcohol alcohol = (Alcohol) o;
        return Objects.equals(name, alcohol.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

}
