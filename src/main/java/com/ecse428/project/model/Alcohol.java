package com.ecse428.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Alcohol {

    @Id
    @Column( name = "alcohol_name")
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

}
