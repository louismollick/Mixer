package com.ecse428.project.config;

import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.Cocktail;
import com.ecse428.project.model.Modifier;
import com.ecse428.project.model.Modifier.ModifierType;
import com.ecse428.project.repository.AlcoholRepository;
import com.ecse428.project.repository.CocktailRepository;
import com.ecse428.project.repository.ModifierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CocktailConfig {

    @Bean
    CommandLineRunner cocktailCommandLineRunner(CocktailRepository cocktailRepository, AlcoholRepository alcoholRepository,
                                                ModifierRepository modifierRepository){
        return args -> {
            Alcohol vodka = new Alcohol("Vodka");
            Alcohol gin = new Alcohol("Gin");
            Alcohol whiskey = new Alcohol("Whiskey");
            Alcohol tequila = new Alcohol("Tequila");
            Alcohol rum = new Alcohol("Rum");
            Alcohol scotch = new Alcohol("Scotch");
            Alcohol brandy = new Alcohol("Brandy");
            Alcohol bourbon = new Alcohol("Bourbon");
            Alcohol cointreau = new Alcohol("Cointreau");

            Modifier sugarSyrup = new Modifier("Sugar Syrup", ModifierType.SYRUP);
            Modifier limeJuice = new Modifier("Lime Juice", ModifierType.JUICE);
            Modifier lemonJuice = new Modifier("Lemon Juice", ModifierType.JUICE);
            Modifier vermouth = new Modifier("Vermouth", ModifierType.FORTIFIED_WINE);
            Modifier tonic = new Modifier("Tonic", ModifierType.JUICE);
            Modifier soda = new Modifier("Soda", ModifierType.JUICE);
            Modifier tomatoJuice = new Modifier("Tomato Juice", ModifierType.JUICE);

            Cocktail ginAndTonic = new Cocktail("Gin and Tonic", List.of(tonic), List.of(gin));
            Cocktail vodkaSoda = new Cocktail("Vodka Soda", List.of(soda), List.of(vodka));
            Cocktail margarita = new Cocktail("Margarita", List.of(limeJuice), List.of(cointreau, tequila));

            alcoholRepository.saveAll(List.of(vodka, gin, whiskey, tequila, rum, scotch, brandy, bourbon, cointreau));
            modifierRepository.saveAll(List.of(sugarSyrup, limeJuice, lemonJuice, vermouth, tonic, soda, tomatoJuice));
            cocktailRepository.saveAll(List.of(ginAndTonic, vodkaSoda, margarita));
        };
    }
}
