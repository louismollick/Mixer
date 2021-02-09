package com.ecse428.project.service;
import java.util.List;
import com.ecse428.project.model.Alcohol;
import com.ecse428.project.repository.AlcoholRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IAlcoholService implements AlcoholService {
    
    private final AlcoholRepository alcoholRepository;

    @Autowired
    public IAlcoholService(AlcoholRepository alcoholRepository){
        this.alcoholRepository = alcoholRepository;
    }

    public List<Alcohol> getAlcohol(){
        return alcoholRepository.findAll();
    }
}
