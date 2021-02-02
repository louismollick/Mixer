package com.ecse428.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Modifier {
  public enum ModifierType {
    FORTIFIED_WINE, LIQUER, SYRUP, JUICE, SMOOTHING_AGENT
  }

  @Id
  @SequenceGenerator(
    name = "modifier_sequence",
    sequenceName = "modifier_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "modifier_sequence"
  )
  private Long id;
  private String name;
  private ModifierType type;

  public Modifier() {
  }

  public Modifier(String name, ModifierType type) {
    this.name = name;
    this.type = type;
  }

  public Modifier(Long id, String name, ModifierType type) {
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

  public ModifierType getType() {
    return this.type;
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", type='" + getType() + "'" + "}";
  }
}
