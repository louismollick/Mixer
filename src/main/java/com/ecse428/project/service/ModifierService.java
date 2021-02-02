package com.ecse428.project.service;

import java.util.List;

import com.ecse428.project.model.Modifier;
import com.ecse428.project.repository.ModifierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifierService {

  private final ModifierRepository modifierRepository;

  @Autowired
  public ModifierService(ModifierRepository modifierRepository) {
    this.modifierRepository = modifierRepository;
  }
  
  public List<Modifier> getModifiers(){
    return modifierRepository.findAll();
  }
}
