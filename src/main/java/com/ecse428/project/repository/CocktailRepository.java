package com.ecse428.project.repository;

import com.ecse428.project.model.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {
    Optional<Cocktail> findByName(String name);
}
