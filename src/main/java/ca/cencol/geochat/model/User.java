package ca.cencol.geochat.model;

import lombok.Value;
import lombok.experimental.Builder;

@Value
@Builder
public class User {

  String userId;
  String username;
  String email;
  String password;
  
  public boolean isValid() {
    return userId != null && !userId.isEmpty() && 
        username != null && !username.isEmpty() &&
        email !=null && !email.isEmpty() &&
        password != null && !password.isEmpty();
  }

}
