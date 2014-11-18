package ca.cencol.geochat.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "New value for a user profile")
public class ProfileNewValue {

  @ApiModelProperty(value = "Updated value", required = true, allowableValues = "Non-empty string")
  private String newValue;
  
  public ProfileNewValue() {
  }

  public String getValue() {
    return newValue;
  }

  public void setValue(String newValue) {
    this.newValue = newValue;
  }

  @Override
  public String toString() {
    return newValue;
  }
  
}
