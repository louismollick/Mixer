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
            Alcohol champagne = new Alcohol("Champagne");
            Alcohol tripleSec = new Alcohol("Triple Sec");

            Modifier sugarSyrup = new Modifier("Sugar Syrup", ModifierType.SYRUP);
            Modifier limeJuice = new Modifier("Lime Juice", ModifierType.JUICE);
            Modifier lemonJuice = new Modifier("Lemon Juice", ModifierType.JUICE);
            Modifier vermouth = new Modifier("Vermouth", ModifierType.FORTIFIED_WINE);
            Modifier tonic = new Modifier("Tonic", ModifierType.JUICE);
            Modifier soda = new Modifier("Soda", ModifierType.JUICE);
            Modifier tomatoJuice = new Modifier("Tomato Juice", ModifierType.JUICE);
            Modifier orangeJuice = new Modifier("Orange Juice", ModifierType.JUICE);
            Modifier grenadine = new Modifier("Grenadine", ModifierType.SYRUP);

            Cocktail ginAndTonic = new Cocktail("Gin and Tonic", List.of(tonic), List.of(gin));
            Cocktail vodkaSoda = new Cocktail("Vodka Soda", List.of(soda), List.of(vodka));
            Cocktail margarita = new Cocktail("Margarita", List.of(limeJuice), List.of(cointreau, tequila));
            Cocktail bloodyMary = new Cocktail("Bloody Mary", List.of(tomatoJuice, lemonJuice), List.of(vodka));
            Cocktail mimosa = new Cocktail("Mimosa", List.of(orangeJuice), List.of(champagne));
            Cocktail daiquiri = new Cocktail("Daiquiri", List.of(limeJuice, sugarSyrup), List.of(rum));
            Cocktail tequilaSunrise = new Cocktail("Tequila Sunrise", List.of(orangeJuice, grenadine), List.of(tequila));
            Cocktail orangeMargarita = new Cocktail("Orange Margarita", List.of(orangeJuice, limeJuice), List.of(tequila, tripleSec));

            alcoholRepository.saveAll(List.of(vodka, gin, whiskey, tequila, rum, scotch, brandy, bourbon, cointreau, champagne, tripleSec));
            modifierRepository.saveAll(List.of(sugarSyrup, limeJuice, lemonJuice, vermouth, tonic, soda, tomatoJuice, orangeJuice, grenadine));
            cocktailRepository.saveAll(List.of(ginAndTonic, vodkaSoda, margarita, bloodyMary, mimosa, daiquiri, tequilaSunrise, orangeMargarita));
        };
    }
}
