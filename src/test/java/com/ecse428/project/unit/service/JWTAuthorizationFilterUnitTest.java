package com.ecse428.project.unit.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import com.ecse428.project.auth.JWTAuthenticationFilter;
import com.ecse428.project.auth.JWTAuthorizationFilter;
import com.ecse428.project.repository.UserRepository;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class JWTAuthorizationFilterUnitTest {

  @Mock
  private static MockHttpServletRequest request;

  @Mock
  private static MockHttpServletResponse response;

  @Mock
  UserRepository userRepository;

  @Mock
  AuthenticationManager authenticationManager;

  @Mock
  FilterChain filterChain;

  @InjectMocks
  JWTAuthorizationFilter jwtAuthorizationFilter;

  @InjectMocks
  JWTAuthenticationFilter jwtAuthenticationFilter;

  @Test
  public void doesntAuthenticateWithoutToken() throws Exception {
    JWTAuthenticationFilter spy1 = Mockito.spy(jwtAuthenticationFilter);
    JWTAuthorizationFilter spy2 = Mockito.spy(jwtAuthorizationFilter);

    spy2.doFilterInternal(request, response, filterChain);

    verify(spy2, never()).getAuthentication(request);
    verify(spy1, never()).successfulAuthentication(eq(request), eq(response), eq(filterChain), any());
  }

  @Test
  public void doFilterInternalTest() throws IOException, ServletException {
    request.addHeader("Authorization",
        "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VybmFtZUBnbWFpbC5jb20iLCJpYXQiOjE1MTYyMzkwMjJ9.YxTPVL0BKWTV2c7Xe6R-zRofqRSr6QWE4TzcTVrHs29REd1rP3gpzRNFTiWImuIbgxoIgvU2hMc-KUDJritiaQ");
    jwtAuthorizationFilter.doFilterInternal(request, response, filterChain);

    verify(filterChain, times(1)).doFilter(request, response);
  }
}
