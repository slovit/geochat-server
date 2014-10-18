package ca.cencol.geochat.model;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Builder;

@Value
@Builder
public class User {

  @NonNull
  String userId;
  String username;
  String email;
  String password;

}
