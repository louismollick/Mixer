package com.ecse428.project.controller;
import java.util.List;
import com.ecse428.project.model.Alcohol;
import com.ecse428.project.service.IAlcoholService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/alcohol")
public class AlcoholController {
    private final IAlcoholService alcoholService;

    @Autowired
    public AlcoholController(IAlcoholService alcoholService){
        this.alcoholService = alcoholService;
    }
    
    @GetMapping
    public List<Alcohol> getAlcohol(){
        return alcoholService.getAlcohol();
    }
}
