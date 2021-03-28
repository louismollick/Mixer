package com.ecse428.project.unit.controller;

import com.ecse428.project.auth.UserDetailsServiceImpl;
import com.ecse428.project.controller.AuthController;
import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.Modifier;
import com.ecse428.project.model.User;
import com.ecse428.project.service.IUserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)
public class DeleteAccountControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IUserService service;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void givenValidID_whenDeleteAccount_thenSuccess() throws Exception {

        long id = 53;
        Set<Modifier> modifiers = new HashSet<Modifier>();
        Set<Alcohol> alcohols = new HashSet<Alcohol>();
        String email = "sample.email@gmail.com";
        User newUser = new User(id,email, "987654321", alcohols, modifiers);

        service.postSignup(newUser);

        given(service.deleteAccount(id)).willReturn(ResponseEntity.status(HttpStatus.OK).body("Succesfully deleted."));

        mvc.perform(MockMvcRequestBuilders.delete("/delete/users")
                .content(String.format("{\"userId\":\"%s\"}", "53"))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string("Succesfully deleted."));
    }

    @Test
    public void givenInvalidID_whenDeleteAccount_thenError() throws Exception {

        long id = 53;
        Set<Modifier> modifiers = new HashSet<Modifier>();
        Set<Alcohol> alcohols = new HashSet<Alcohol>();
        String email = "sample.email@gmail.com";
        User newUser = new User(id,email, "987654321", alcohols, modifiers);



        given(service.deleteAccount(id)).willReturn(ResponseEntity.status(HttpStatus.OK).body("Succesfully deleted."));

        mvc.perform(MockMvcRequestBuilders.delete("/delete/users")
                .content(String.format("{\"userId\":\"%s\"}", "53"))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
                .andExpect(content().string("User not found with id."));
    }


}
