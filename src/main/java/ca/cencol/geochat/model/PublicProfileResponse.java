package ca.cencol.geochat.model;

import lombok.Value;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@Value
@ApiModel(value = "Public profile response")
public class PublicProfileResponse {

  @ApiModelProperty(value = "Unique user ID", required = true)
  String userId;

  @ApiModelProperty(value = "Username", required = true)
  String username;
  
  @ApiModelProperty(value = "Image ID", required = true)
  String imageId;

  @ApiModelProperty(value = "Additional info", required = true)
  String additionalInfo;
}
