package com.ecse428.project.config;
import java.util.List;
import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.Alcohol.AlcoholType;
import com.ecse428.project.repository.AlcoholRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AlcoholConfig {
    
    
  @Bean
  CommandLineRunner commandLineRunner(AlcoholRepository alcoholRepository){
    return args -> {
      Alcohol vodka = new Alcohol("Vodka", AlcoholType.VODKA);
      Alcohol gin = new Alcohol("Gin", AlcoholType.GIN);
      Alcohol whiskey = new Alcohol("Whiskey", AlcoholType.WHISKEY);
      Alcohol tequila = new Alcohol("Tequila", AlcoholType.TEQUILA);
      Alcohol rum = new Alcohol("Rum", AlcoholType.RUM);
      Alcohol scotch = new Alcohol("Scotch", AlcoholType.SCOTCH);
      Alcohol brandy = new Alcohol("Brandy", AlcoholType.BRANDY);
      Alcohol bourbon = new Alcohol("Bourbon", AlcoholType.BOURBON);

      alcoholRepository.saveAll(
        List.of(vodka, gin, whiskey, tequila, rum, scotch, brandy, bourbon)
      );
    };
  }

}

  //vodka
  //gin
  //whiskey
  //tequila
  //rum
  //scotch
  //brandy
  //bourbon