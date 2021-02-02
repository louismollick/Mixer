package com.ecse428.project.repository;

import java.util.UUID;

import com.ecse428.project.model.Modifier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModifierRepository extends JpaRepository<Modifier, UUID> {
  
}
