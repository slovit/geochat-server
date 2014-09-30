package ca.cencol.geochat.model;

import java.util.Date;

public class Message {

  private final String userId;
  private final String username;
  private final Date time;
  private final String message;

  public Message(String userId, String username, String message) {
    this.userId = userId;
    this.username = username;
    this.time = new Date();
    this.message = message;
  }

  public String getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }

  public Date getTime() {
    return time;
  }

  public String getMessage() {
    return message;
  }
  
  @Override
  public String toString() {
    return String.format("UserId: %s", userId);
  }

}
