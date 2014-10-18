package ca.cencol.geochat.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import lombok.Data;

@Data
@ApiModel(value = "Login user information")
public class LoginRequest {

  @ApiModelProperty(value = "User's email. Currently any string is valid", required = true)
  private String email;
  @ApiModelProperty(value = "User's password. Currently any string is valid", required = true)
  private String password;
  
}
