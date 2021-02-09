package com.ecse428.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table
public class Alcohol {

    public enum AlcoholType{
        WINE, WHISKY, GIN, VODKA, TAQUILA, RUM
    }

    @Id
    @SequenceGenerator(
      name = "alcohol_sequence",
      sequenceName = "alcohol_sequence",
      allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "alcohol_sequence"
    )

    private Long id;
    private String name;
    private AlcoholType type;
    private int alcohol_concentration;

    public Alcohol(){
    }

    public Alcohol(String name, AlcoholType type) {
        this.name = name;
        this.type = type;
        this.alcohol_concentration = alcohol_concentration;
    }

    public Alcohol(Long id, String name, AlcoholType type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.alcohol_concentration = alcohol_concentration
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public AlcoholType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", type='" + getType() + "'" + "}";
    }


}
