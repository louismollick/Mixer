package com.ecse428.project.service;

import java.util.Set;

import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.Modifier;

public interface UserService {
  public Set<Modifier> getModifiersInInventory(long userId);

  public void putModifierInInventory(long userId, String modifierName);

  public Set<Alcohol> getAlcoholInInventory(long userId);

  public void putAlcoholInInventory(long userId, String alcoholName);
}
