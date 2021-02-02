package com.ecse428.project.controller;

import java.util.List;

import com.ecse428.project.model.Modifier;
import com.ecse428.project.service.ModifierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/modifier")
public class ModifierController {
  private final ModifierService modifierService;

  @Autowired
  public ModifierController(ModifierService modifierService) {
    this.modifierService = modifierService;
  }

  @GetMapping
  public List<Modifier> getModifiers(){
    return modifierService.getModifiers();
  }
}
