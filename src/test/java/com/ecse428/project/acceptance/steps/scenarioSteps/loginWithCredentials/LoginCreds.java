package com.ecse428.project.acceptance.steps.scenarioSteps.loginWithCredentials;

import java.util.Objects;

public class LoginCreds {

  private String email;
  private String password;

  public LoginCreds() {
  }

  public LoginCreds(String email, String password) {
    this.email = email;
    this.password = password;
  }


  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LoginCreds)) {
            return false;
        }
        LoginCreds loginCreds = (LoginCreds) o;
        return Objects.equals(email, loginCreds.email) && Objects.equals(password, loginCreds.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password);
  }


  @Override
  public String toString() {
    return "{" +
      " email='" + getEmail() + "'" +
      ", password='" + getPassword() + "'" +
      "}";
  }  
}
