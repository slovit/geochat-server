package ca.cencol.geochat.model;

import lombok.Value;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@Value
@ApiModel(value = "Registration response")
public class RegistrationResponse {

  @ApiModelProperty(value = "Unique user ID", required = true)
  String userId;
  
}
