package ca.cencol.geochat.model;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.List;

import com.google.common.collect.Lists;


public class ChatRoom {
  private final String roomId;
  
  // FIXME: implement with a fixed-size queue
  private List<Message> messages = Lists.newArrayList();

  public ChatRoom(String locationId) {
    checkState(!isNullOrEmpty(locationId));
    
    this.roomId = locationId;
  }

  
  public String getRoomId() {
    return roomId;
  }
  
  public void postMessage(String userId, String message) {
    checkState(!isNullOrEmpty(userId) && !isNullOrEmpty(message));
    messages.add(createMessage(userId, message));
  }
  
  public List<Message> getMessages() {
    return messages;
  }
  
  private Message createMessage(String userId, String message) {
    return new Message(userId, userId, message);
  }
  
}
