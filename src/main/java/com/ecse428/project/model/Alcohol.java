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
        VODKA, GIN, WHISKEY, TEQUILA, RUM, SCOTCH, BRANDY, BOURBON
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

    public Alcohol(){
    }

    public Alcohol(String name, AlcoholType type) {
        this.name = name;
        this.type = type;
    }

    public Alcohol(Long id, String name, AlcoholType type) {
        this.id = id;
        this.name = name;
        this.type = type;
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


  //vodka
  //gin
  //whiskey
  //tequila
  //rum
  //scotch
  //brandy
  //bourbon

}
