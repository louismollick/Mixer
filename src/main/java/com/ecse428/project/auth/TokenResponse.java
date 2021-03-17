package com.ecse428.project.auth;

public class TokenResponse {
  private String token;
  private String email;
  private long userId;

  public TokenResponse(String token, String email, long userId) {
    this.token = token;
    this.email = email;
    this.userId = userId;
  }


  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public long getUserId() {
    return this.userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

}
