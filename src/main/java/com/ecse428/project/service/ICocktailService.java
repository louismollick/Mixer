package com.ecse428.project.service;

import java.util.*;
import java.util.stream.Collectors;

import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.Cocktail;
import com.ecse428.project.model.Modifier;
import com.ecse428.project.repository.AlcoholRepository;
import com.ecse428.project.repository.CocktailRepository;

import com.ecse428.project.repository.ModifierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;

@Service
public class ICocktailService implements CocktailService{

    @Autowired
    private final CocktailRepository cocktailRepository;

    @Autowired
    private final AlcoholRepository alcoholRepository;

    @Autowired
    private final ModifierRepository modifierRepository;

    @Autowired
    public ICocktailService(CocktailRepository cocktailRepository, AlcoholRepository alcoholRepository, ModifierRepository modifierRepository) {
        this.cocktailRepository = cocktailRepository;
        this.alcoholRepository = alcoholRepository;
        this.modifierRepository = modifierRepository;
    }

    @Override
    public List<Cocktail> getCocktail(){
        return cocktailRepository.findAll();
    }

    @Override
    public List<Cocktail> getCocktailByParams(String cName, List<String> alcohols, List<String> modifiers, List<String> tasteTypes,
                                              String strengthType, String servingSize) {
        final List<Alcohol> a_list = new ArrayList<>();
        final List<Modifier> m_list = new ArrayList<>();
        final List<Cocktail.TasteType> t_list = new ArrayList<>();
        final Cocktail.StrengthType st_found;
        final Cocktail.ServingSize ss_found;
        final String cName_res = cName == null ? new String() : cName;

        if (alcohols != null && !alcohols.isEmpty()) {
            for (String a : alcohols) {
                Optional<Alcohol> a_found = alcoholRepository.findByName(getCase(a));

                if (a_found.isPresent()) {
                    a_list.add(a_found.get());
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, a + " is not a valid alcohol.");
                }
            }
        } else {
            a_list.add(null);
        }

        if (modifiers != null && !modifiers.isEmpty()) {
            for (String m : modifiers) {
                Optional<Modifier> m_found = modifierRepository.findByName(getCase(m));

                if( m_found.isPresent()) {
                    m_list.add(m_found.get());
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, m + " is not a valid modifier.");
                }
            }
        } else {
            m_list.add(null);
        }

        if (tasteTypes != null && !tasteTypes.isEmpty()) {
            for (String t : tasteTypes) {
                Cocktail.TasteType t_found;

                try {
                    t_found = (t == null || t.isEmpty()) ? null : Cocktail.TasteType.valueOf(t.toUpperCase());
                } catch (Exception e){
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, t + " is not a valid taste type.");
                }

                t_list.add(t_found);
            }
        } else {
            t_list.add(null);
        }

        List<Cocktail> cocktails;
        List<Cocktail> res = new ArrayList<Cocktail>();

        st_found = (strengthType == null || strengthType.isEmpty()) ? null : Cocktail.StrengthType.valueOf(strengthType.toUpperCase());
        ss_found = (servingSize == null || servingSize.isEmpty()) ? null : Cocktail.ServingSize.valueOf(servingSize.toUpperCase());

        for (Alcohol a : a_list) {
            for (Modifier m : m_list) {
                for (Cocktail.TasteType t : t_list){
                    cocktails = cocktailRepository.findByParams(cName_res, a, m, t, st_found, ss_found);
                    for (Cocktail c : cocktails){
                        if (!res.contains(c)){
                            res.add(c);
                        }
                    }
                }
            }
        }

        return res;
    }

    @NotNull
    private String getCase (@NotNull String anyCap) {
        return Arrays.stream(anyCap.split("\\s+"))
                .map(t -> t.substring(0, 1).toUpperCase() + t.substring(1))
                .collect(Collectors.joining(" "));
    }
}
