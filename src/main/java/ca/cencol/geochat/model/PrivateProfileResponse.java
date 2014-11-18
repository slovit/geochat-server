package ca.cencol.geochat.model;

import lombok.Value;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@Value
@ApiModel(value = "Private profile response")
public class PrivateProfileResponse {

  @ApiModelProperty(value = "Unique user ID", required = true)
  String userId;

  @ApiModelProperty(value = "Username", required = true)
  String username;

  @ApiModelProperty(value = "Email", required = true)
  String email;
  
  @ApiModelProperty(value = "Image ID", required = true)
  String imageId;

  @ApiModelProperty(value = "Additional info", required = true)
  String additionalInfo;

}
