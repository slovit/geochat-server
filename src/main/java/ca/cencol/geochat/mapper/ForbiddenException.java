package ca.cencol.geochat.mapper;

public class ForbiddenException extends RuntimeException {

  private final String message;

  public ForbiddenException(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
