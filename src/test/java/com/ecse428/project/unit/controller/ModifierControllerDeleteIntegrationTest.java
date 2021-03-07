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
public class ModifierControllerDeleteIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IUserService userService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    @WithMockUser
    public void givenModifiers_whenDeleteModifiers_thenReturnNewJsonArray() throws Exception {
        long num = 44;
        // Optional<User> user = userRepository.findById(userId);
        Modifier redBull = new Modifier("RedBull", ModifierType.SMOOTHING_AGENT);
        Modifier madiera = new Modifier("Madiera", ModifierType.FORTIFIED_WINE);
        Modifier orangeJuice = new Modifier("OrangeJuice", ModifierType.JUICE);
        Set<Modifier> modifiersInInventory = new HashSet<Modifier>();
        modifiersInInventory.add(redBull);
        modifiersInInventory.add(madiera);
        modifiersInInventory.add(orangeJuice);
        Set<Alcohol> alcoholInInventory = new HashSet<Alcohol>();
        User newUser = new User(num,"newUser@gmail.com","abcdefg", alcoholInInventory, modifiersInInventory);

        given(userService.getModifiersInInventory(num)).willReturn(modifiersInInventory);

        String uri_req = "/api/user/"+ num +"/modifier/Madiera";
        mvc.perform(MockMvcRequestBuilders.delete(uri_req))
                .andExpect(status().isOk());
        
        String new_uri_req = "/api/user/"+ num +"/modifier";
        mvc.perform(MockMvcRequestBuilders.get(new_uri_req))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}