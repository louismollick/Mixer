package com.ecse428.project.unit.controller;

import com.ecse428.project.auth.UserDetailsServiceImpl;
import com.ecse428.project.controller.AuthController;
import com.ecse428.project.controller.UserController;
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
import org.springframework.security.test.context.support.WithMockUser;
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
@WebMvcTest(UserController.class)
public class DeleteAccountControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IUserService service;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    @WithMockUser
    public void givenValidID_whenDeleteAccount_thenSuccess() throws Exception {
        given(service.deleteAccount(54)).willReturn(ResponseEntity.status(HttpStatus.OK).body("Successfully deleted."));

        mvc.perform(MockMvcRequestBuilders.delete("/api/user/54/delete"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void givenInvalidID_whenDeleteAccount_thenError() throws Exception {
        given(service.deleteAccount(53)).willReturn(ResponseEntity.status(HttpStatus.resolve(400)).body("User not found with id."));

        mvc.perform(MockMvcRequestBuilders.delete("/api/user/53/delete")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
                .andExpect(content().string("User not found with id."));
    }
}
