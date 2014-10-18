package ca.cencol.geochat.model;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Collection;
import java.util.Queue;

import com.google.common.collect.EvictingQueue;

public class ChatRoom {
  
  public static final int MAX_MESSAGES = 20;

  private final String roomId;
  private Queue<Message> messages = EvictingQueue.create(MAX_MESSAGES);

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

  public Collection<Message> getMessages() {
    return messages;
  }

  private Message createMessage(String userId, String message) {
    return new Message(userId, userId, message);
  }

}
