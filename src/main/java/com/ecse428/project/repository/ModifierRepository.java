package com.ecse428.project.repository;

import java.util.List;
import java.util.Optional;

import com.ecse428.project.model.Modifier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModifierRepository extends JpaRepository<Modifier, String> {
  Optional<Modifier> findByName(String name);
  List<Modifier> findAll();
}
