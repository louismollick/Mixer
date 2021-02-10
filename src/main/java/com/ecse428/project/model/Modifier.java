package com.ecse428.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Modifier {
  public enum ModifierType {
    FORTIFIED_WINE, LIQUER, SYRUP, JUICE, SMOOTHING_AGENT
  }

  @Id
  @Column( name = "modifier_name")
  private String name;
  private ModifierType type;

  public Modifier() {
  }

  public Modifier(String name, ModifierType type) {
    this.name = name;
    this.type = type;
  }

  public String getName() {
    return this.name;
  }

  public ModifierType getType() {
    return this.type;
  }

  @Override
  public String toString() {
    return "{" + " name='" + getName() + "'" + ", type='" + getType() + "'" + "}";
  }

}
