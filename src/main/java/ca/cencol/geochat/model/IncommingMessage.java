package ca.cencol.geochat.model;


public class IncommingMessage {
  
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
