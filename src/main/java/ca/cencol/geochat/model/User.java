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
  String imageId;
  String additionalInfo;

  public boolean isValid() {
    return userId != null && !userId.isEmpty() &&
        username != null && !username.isEmpty() &&
        email != null && !email.isEmpty() &&
        password != null && !password.isEmpty() &&
        imageId != null &&
        additionalInfo != null;
  }
}

