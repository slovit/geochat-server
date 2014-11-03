package ca.cencol.geochat.model;

import lombok.Data;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@Data
@ApiModel(value = "Update user profile information")
public class UpdateUser {

  @ApiModelProperty(value = "Unique user ID", required = true)
  String userId;
  
  @ApiModelProperty(value = "Username", required = true)
  String username;

  @ApiModelProperty(value = "User's password", required = true)
  private String password;

  @ApiModelProperty(value = "Email", required = true)
  String email;

  @ApiModelProperty(value = "Image ID", required = true)
  String imageId;

  @ApiModelProperty(value = "Additional info", required = true)
  String additionalInfo;
}
