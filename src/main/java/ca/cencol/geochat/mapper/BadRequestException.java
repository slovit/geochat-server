package ca.cencol.geochat.mapper;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class BadRequestException extends RuntimeException {
  
  String message;

}
