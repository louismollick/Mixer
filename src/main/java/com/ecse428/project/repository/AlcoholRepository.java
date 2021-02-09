package com.ecse428.project.repository;
import com.ecse428.project.model.Alcohol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlcoholRepository extends JpaRepository<Alcohol, Long> {
}
