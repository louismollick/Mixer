package com.ecse428.project.unit.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.Cocktail;
import com.ecse428.project.model.Modifier;
import com.ecse428.project.repository.AlcoholRepository;
import com.ecse428.project.repository.CocktailRepository;
import com.ecse428.project.repository.ModifierRepository;
import com.ecse428.project.service.ICocktailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
public class ICocktailServiceTest {
    @MockBean
    private CocktailRepository mockCocktailRepository;

    @MockBean
    private AlcoholRepository mockAlcoholRepository;

    @MockBean
    private ModifierRepository mockModifierRepository;

    @SpyBean
    private ICocktailService testICocktailService;

    @Before
    public void setUp() {
        Alcohol vodka = new Alcohol("Vodka");
        Modifier sugarSyrup = new Modifier("Sugar Syrup", Modifier.ModifierType.SYRUP);
        Cocktail c1 = new Cocktail("name1", List.of(sugarSyrup), List.of(vodka), Set.of(Cocktail.TasteType.BITTER), Cocktail.StrengthType.MEDIUM, Cocktail.ServingSize.INDIVIDUAL, "");

        List<Alcohol> alist = new ArrayList<>();
        alist.add(vodka);

        List<Modifier> mlist = new ArrayList<>();
        mlist.add(sugarSyrup);

        List<Cocktail> clist = new ArrayList<>();
        clist.add(c1);

        when(mockAlcoholRepository.findAll()).thenReturn(alist);
        when(mockAlcoholRepository.findByName("Vodka")).thenReturn(java.util.Optional.of(vodka));
        when(mockModifierRepository.findAll()).thenReturn(mlist);
        when(mockModifierRepository.findByName("Sugar Syrup")).thenReturn(java.util.Optional.of(sugarSyrup));

        testICocktailService = spy(new ICocktailService(mockCocktailRepository, mockAlcoholRepository, mockModifierRepository));
        }

    @Test
    public void testGetAllCocktails() {
        Cocktail c0 = new Cocktail("name1", null, null, Set.of(Cocktail.TasteType.BITTER), Cocktail.StrengthType.MEDIUM, Cocktail.ServingSize.INDIVIDUAL, "");
        List<Cocktail> clist = new ArrayList<>();

        clist.add(c0);

        when(mockCocktailRepository.findAll()).thenReturn(clist);

        List<Cocktail> cList = testICocktailService.getCocktail();
        assertTrue(!cList.isEmpty());
    }

    @Test
    public void testGetCocktailByParams() {
        String cName = "c";
        List<String> alcohols = List.of("vodka");
        List<String> modifiers = List.of("sugar Syrup");
        List<String> tasteTypes = List.of("bitter");
        String strengthType = "MEDIUM";
        String servingSize = "INDIVIDUAL";

        Alcohol vodka = new Alcohol("Vodka");
        Modifier sugarSyrup = new Modifier("Sugar Syrup", Modifier.ModifierType.SYRUP);
        Cocktail c1 = new Cocktail("name1", List.of(sugarSyrup), List.of(vodka), Set.of(Cocktail.TasteType.BITTER), Cocktail.StrengthType.MEDIUM, Cocktail.ServingSize.INDIVIDUAL, "");

        when(mockCocktailRepository.findByParams(cName, vodka, sugarSyrup, Cocktail.TasteType.BITTER, Cocktail.StrengthType.MEDIUM, Cocktail.ServingSize.INDIVIDUAL)).thenReturn(List.of(c1));

        List<Cocktail> cList = testICocktailService.getCocktailByParams(cName, alcohols, modifiers, tasteTypes, strengthType, servingSize);
        assertTrue(!cList.isEmpty());
    }

    @Test
    public void testGetCocktailByParamsInvalidAlcohol() {
        String cName = "c";
        List<String> alcohols = List.of("v");
        List<String> modifiers = List.of("sugar Syrup");
        List<String> tasteTypes = List.of("bitter");
        String strengthType = "MEDIUM";
        String servingSize = "INDIVIDUAL";
        boolean exceptionCaught = false;

        Alcohol vodka = new Alcohol("Vodka");
        Modifier sugarSyrup = new Modifier("Sugar Syrup", Modifier.ModifierType.SYRUP);
        Cocktail c1 = new Cocktail("name1", List.of(sugarSyrup), List.of(vodka), Set.of(Cocktail.TasteType.BITTER), Cocktail.StrengthType.MEDIUM, Cocktail.ServingSize.INDIVIDUAL, "");

        when(mockCocktailRepository.findByParams(cName, vodka, sugarSyrup, Cocktail.TasteType.BITTER, Cocktail.StrengthType.MEDIUM, Cocktail.ServingSize.INDIVIDUAL)).thenReturn(List.of(c1));

        try {
            testICocktailService.getCocktailByParams(cName, alcohols, modifiers, tasteTypes, strengthType, servingSize);
        } catch (Exception e) {
            exceptionCaught = true;
        }

        assertTrue(exceptionCaught);
    }

    @Test
    public void testGetCocktailByParamsInvalidModifier() {
        String cName = "c";
        List<String> alcohols = List.of("vodka");
        List<String> modifiers = List.of("s");
        List<String> tasteTypes = List.of("bitter");
        String strengthType = "MEDIUM";
        String servingSize = "INDIVIDUAL";
        boolean exceptionCaught = false;

        Alcohol vodka = new Alcohol("Vodka");
        Modifier sugarSyrup = new Modifier("Sugar Syrup", Modifier.ModifierType.SYRUP);
        Cocktail c1 = new Cocktail("name1", List.of(sugarSyrup), List.of(vodka), Set.of(Cocktail.TasteType.BITTER), Cocktail.StrengthType.MEDIUM, Cocktail.ServingSize.INDIVIDUAL, "");

        when(mockCocktailRepository.findByParams(cName, vodka, sugarSyrup, Cocktail.TasteType.BITTER, Cocktail.StrengthType.MEDIUM, Cocktail.ServingSize.INDIVIDUAL)).thenReturn(List.of(c1));

        try {
            testICocktailService.getCocktailByParams(cName, alcohols, modifiers, tasteTypes, strengthType, servingSize);
        } catch (Exception e) {
            exceptionCaught = true;
        }

        assertTrue(exceptionCaught);
    }

    @Test
    public void testGetCocktailByParamsInvalidTasteType() {
        String cName = "c";
        List<String> alcohols = List.of("vodka");
        List<String> modifiers = List.of("sugar Syrup");
        List<String> tasteTypes = List.of("b");
        String strengthType = "MEDIUM";
        String servingSize = "INDIVIDUAL";
        boolean exceptionCaught = false;

        Alcohol vodka = new Alcohol("Vodka");
        Modifier sugarSyrup = new Modifier("Sugar Syrup", Modifier.ModifierType.SYRUP);
        Cocktail c1 = new Cocktail("name1", List.of(sugarSyrup), List.of(vodka), Set.of(Cocktail.TasteType.BITTER), Cocktail.StrengthType.MEDIUM, Cocktail.ServingSize.INDIVIDUAL, "");

        when(mockCocktailRepository.findByParams(cName, vodka, sugarSyrup, Cocktail.TasteType.BITTER, Cocktail.StrengthType.MEDIUM, Cocktail.ServingSize.INDIVIDUAL)).thenReturn(List.of(c1));

        try {
            testICocktailService.getCocktailByParams(cName, alcohols, modifiers, tasteTypes, strengthType, servingSize);
        } catch (Exception e) {
            exceptionCaught = true;
        }

        assertTrue(exceptionCaught);
    }
}