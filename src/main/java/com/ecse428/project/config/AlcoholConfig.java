package com.ecse428.project.config;

import java.util.List;
import com.ecse428.project.model.Alcohol;
import com.ecse428.project.repository.AlcoholRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlcoholConfig {

  @Bean
  CommandLineRunner alcoholCommandLineRunner(AlcoholRepository alcoholRepository) {
    return args -> {
      Alcohol vodka = new Alcohol("Vodka");
      Alcohol gin = new Alcohol("Gin");
      Alcohol whiskey = new Alcohol("Whiskey");
      Alcohol tequila = new Alcohol("Tequila");
      Alcohol rum = new Alcohol("Rum");
      Alcohol scotch = new Alcohol("Scotch");
      Alcohol brandy = new Alcohol("Brandy");
      Alcohol bourbon = new Alcohol("Bourbon");

      alcoholRepository.saveAll(List.of(vodka, gin, whiskey, tequila, rum, scotch, brandy, bourbon));
    };
  }

}