package com.ecse428.project.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import static java.util.Collections.emptyList;

public class AuthUser extends User{

  private static final long serialVersionUID = 1L;
  private long user_id;

  public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
  }

  public AuthUser(String username, String password, long user_id) {
    super(username, password, emptyList());
    this.user_id = user_id;
  }

  public long getUserId() {
    return this.user_id;
  }

}
