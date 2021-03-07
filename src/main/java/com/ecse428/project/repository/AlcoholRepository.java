package com.ecse428.project.repository;

import java.util.List;
import java.util.Optional;

import com.ecse428.project.model.Alcohol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlcoholRepository extends JpaRepository<Alcohol, String> {
  Optional<Alcohol> findByName(String name);
  List<Alcohol> findAll();
}
