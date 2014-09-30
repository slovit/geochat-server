package ca.cencol.geochat.model;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

public class User {

  private String userId;
  private String nickname;

  public User(String userId, String nickname) {
    checkState(!isNullOrEmpty(userId) && !isNullOrEmpty(nickname));

    this.userId = userId;
    this.nickname = nickname;
  }

  public String getUserId() {
    return userId;
  }

  public String getNickname() {
    return nickname;
  }

}
