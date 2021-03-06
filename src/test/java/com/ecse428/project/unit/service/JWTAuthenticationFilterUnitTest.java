package com.ecse428.project.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecse428.project.auth.JWTAuthenticationFilter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@RunWith(MockitoJUnitRunner.class)
public class JWTAuthenticationFilterUnitTest {

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private FilterChain chain;

  @Mock
  private AuthenticationManager authenticationManager;

  @InjectMocks
  private JWTAuthenticationFilter uut;

  @Test
  public void failsWithoutCredentials() throws Exception {
    JWTAuthenticationFilter spy = Mockito.spy(uut);
    when(request.getServletPath()).thenReturn("/api");

    spy.doFilter(request, response, chain);

    verify(spy, never()).attemptAuthentication(request, response);
    verify(spy, never()).successfulAuthentication(eq(request), eq(response), eq(chain), any());
  }

  @Test
  public void parseValidCredentials() throws IOException {
    var email = "username@gmail.com";
    var password = "12345678";
    var mockReq = new MockHttpServletRequest();
    mockReq.setContent(String.format("{ \"email\": \"%s\", \"password\": \"%s\"}", email, password).getBytes());
    when(request.getInputStream()).thenReturn(mockReq.getInputStream());
    uut.setAuthenticationManager(authenticationManager);
    var authentication = mock(Authentication.class);
    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

    var result = uut.attemptAuthentication(request, response);

    assertThat(result).isEqualTo(authentication);
  }

  @Test
  public void failsWhenInvalidFieldTypo() throws IOException {
    var email = "username@gmail.com";
    var password = "12345678";
    var mockReq = new MockHttpServletRequest();
    mockReq.setContent(String.format("{ \"email\": \"%s\",\"passwwwwword\": \"%s\"}", email, password).getBytes());
    when(request.getInputStream()).thenReturn(mockReq.getInputStream());
    assertThrows(RuntimeException.class, () -> {
      uut.attemptAuthentication(request, response);
    });
  }
}