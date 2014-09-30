package ca.cencol.geochat.service;

import java.util.List;

import ca.cencol.geochat.model.Message;

/**
 * The service is used to retrieve and post messages to {@link ChatRoom}
 */
public interface RoomService {

  /**
   * Returns {@link Message}s for {@code userId} by {@code roomId}.<br>
   * If the room does not exist creates a new one and returns an empty list.<br>
   * New messages for the user should be pulled with this service.
   * 
   * @throws IllegalStateException if {@code userId} tries to get messages from not their current {@code roomId}
   */
  List<Message> getMessages(String userId, String roomId);

  /**
   * Return a roomId a {@link User} is currently assigned to.
   */
  String getUserRoom(String userId);

  /**
   * Posts a {@code userId}'s {@code message} to {@code roomId}. The method checks if the users posts message to its
   * current room.
   * 
   * @throws IllegalArgumentException if {@code userId} tries to post a {@code message} not to their current
   * {@code roomId}.
   */
  void postMessage(String userId, String roomId, String message);

  /**
   * Adds {@code userId} to {@code roomId}
   */
  void addUserToRoom(String userId, String roomId);

  /**
   * Removes {@code userId} from it current room.
   */
  void removeUserFromRoom(String userId);
  
  /**
   * Checks if {@code userId}'s current room is {@code roomId}
   */
  boolean isCurrentRoom(String userId, String roomId);

}
