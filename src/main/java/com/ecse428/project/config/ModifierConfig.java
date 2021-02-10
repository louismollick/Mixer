package com.ecse428.project.config;

import java.util.List;

import com.ecse428.project.model.Modifier;
import com.ecse428.project.model.Modifier.ModifierType;
import com.ecse428.project.repository.ModifierRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModifierConfig {

  @Bean
  CommandLineRunner modifierCommandLineRunner(ModifierRepository modifierRepository){
    return args -> {
      Modifier sugarSyrup = new Modifier("Sugar Syrup", ModifierType.SYRUP);
      Modifier limeJuice = new Modifier("Lime Juice", ModifierType.JUICE);
      Modifier lemonJuice = new Modifier("Lemon Juice", ModifierType.JUICE);
      Modifier vermouth = new Modifier("Vermouth", ModifierType.FORTIFIED_WINE);

      modifierRepository.saveAll(
        List.of(sugarSyrup, limeJuice, lemonJuice, vermouth)
      );
    };
  }
}
