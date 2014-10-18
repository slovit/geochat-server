package ca.cencol.geochat.model;

import lombok.Data;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@Data
@ApiModel(value = "Registration user information")
public class RegistrationUser {

  @ApiModelProperty(value = "Username(nickname) displayed to the other users in a chat room", required = true)
  private String username;
  @ApiModelProperty(value = "User's password. Currently any string is valid", required = true)
  private String password;
  @ApiModelProperty(value = "User's email. Currently any string is valid", required = true)
  private String email;

}
