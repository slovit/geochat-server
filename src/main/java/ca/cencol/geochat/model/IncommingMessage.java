package ca.cencol.geochat.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "A message posted by the user")
public class IncommingMessage {
  
  @ApiModelProperty(value = "Message text", required = true, allowableValues = "Non-empty string")
  private String message;
  
  public IncommingMessage() {
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return message;
  }

}
