package com.ecse428.project.unit.controller;

import java.util.*;

import com.ecse428.project.auth.UserDetailsServiceImpl;
import com.ecse428.project.controller.ModifierController;
import com.ecse428.project.controller.UserController;
import com.ecse428.project.model.Modifier;
import com.ecse428.project.model.User;
import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.Modifier.ModifierType;
import com.ecse428.project.service.IModifierService;
import com.ecse428.project.service.IUserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class AlcoholControllerDeleteIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IUserService userService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    @WithMockUser
    public void givenAlcohols_whenDeleteAlcohols_thenReturnNewJsonArray() throws Exception {
        long id = 44;
  
        Alcohol vodka = new Alcohol("Vodka");
        Alcohol gin = new Alcohol("Gin");
        Set<Alcohol> alcoholsInInventory = new HashSet<Alcohol>();
        Set<Modifier> modifiersInInventory = new HashSet<Modifier>();
        alcoholsInInventory.add(vodka);
        alcoholsInInventory.add(gin);
        User newUser = new User(id,"user@gmail.com","123456789", alcoholsInInventory, modifiersInInventory);

        given(userService.getAlcoholInInventory(id)).willReturn(alcoholsInInventory);

        String uri_req = "/api/user/"+ id +"/modifier/Vodka";
        mvc.perform(MockMvcRequestBuilders.delete(uri_req))
                .andExpect(status().isOk());
    }
}