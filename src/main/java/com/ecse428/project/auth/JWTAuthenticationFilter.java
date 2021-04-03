package com.ecse428.project.auth;

import com.auth0.jwt.JWT;
import com.ecse428.project.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.ecse428.project.auth.SecurityConstants.EXPIRATION_TIME;
import static com.ecse428.project.auth.SecurityConstants.HEADER_STRING;
import static com.ecse428.project.auth.SecurityConstants.SECRET;
import static com.ecse428.project.auth.SecurityConstants.TOKEN_PREFIX;

/**
 * https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-security
 * The UsernamePasswordAuthenticationFilter, which is provided by Spring
 * Security, registers itself as the responsible for the /login endpoint. As
 * such, whenever your backend API gets a request to /login, your specialization
 * of this filter (i.e., JWTAuthenticationFilter) goes into action and handles
 * the authentication attempt (through the attemptAuthentication method).
 * 
 * source:
 * https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  public JWTAuthenticationFilter() {
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
      throws AuthenticationException {
    try {
      User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);

      if (creds.getEmail() == null || creds.getEmail().isEmpty())
        throw new BadCredentialsException("Email cannot be empty.", new MissingCredentialException());

      if (creds.getPassword() == null || creds.getPassword().isEmpty())
        throw new BadCredentialsException("Password cannot be empty.", new MissingCredentialException());

      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
      Authentication auth) throws IOException, ServletException {
    var details = (AuthUser) auth.getPrincipal();
    String token = JWT.create().withSubject(details.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(HMAC512(SECRET.getBytes()));

    // put token in body and header, since CORS prevents getting it from headers
    // without more work
    var tokenStr = TOKEN_PREFIX + token;
    res.addHeader(HEADER_STRING, tokenStr);
    res.getWriter().write(
        new ObjectMapper().writeValueAsString(new TokenResponse(tokenStr, details.getUsername(), details.getUserId())));
    res.getWriter().flush();
  }

  @Override
  public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse res,
      AuthenticationException failed) throws IOException, ServletException {
    if (failed.getCause() instanceof MissingCredentialException)
      res.getWriter().write(failed.getMessage());
    else
      res.getWriter().write("Invalid credentials. Please try again.");
    res.setStatus(400);
    res.getWriter().flush();
  }

  private class MissingCredentialException extends Throwable {
  }
}