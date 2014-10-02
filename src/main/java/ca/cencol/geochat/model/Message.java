package ca.cencol.geochat.model;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "A single chat message returned by the API")
public class Message {

  @ApiModelProperty(value = "Unique user identifier", required = true)
  private final String userId;
  @ApiModelProperty(value = "User's nickname", required = true)
  private final String username;
  @ApiModelProperty(value = "When the message was posted", required = true)
  private final Date time;
  @ApiModelProperty(value = "Message text", required = true, allowableValues = "Non-empty string")
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
