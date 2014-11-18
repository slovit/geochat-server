package ca.cencol.geochat.dao.mysql;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ColumnNames {
  
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class User {
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String IMAGE_ID = "image_id";
    public static final String ADDITIONAL_INFO = "additional_info";
  }

}
