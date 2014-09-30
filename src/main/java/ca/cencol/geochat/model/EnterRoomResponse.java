package ca.cencol.geochat.model;

import java.util.List;


public class EnterRoomResponse {
  private final String roomId;
  private final List<Message> messages;
  
  public EnterRoomResponse(String roomId, List<Message> messages) {
    super();
    this.roomId = roomId;
    this.messages = messages;
  }

  
  public String getRoomId() {
    return roomId;
  }

  
  public List<Message> getMessages() {
    return messages;
  }
  
}
