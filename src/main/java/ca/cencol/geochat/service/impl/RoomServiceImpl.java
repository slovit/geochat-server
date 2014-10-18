package ca.cencol.geochat.service.impl;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.String.format;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import ca.cencol.geochat.dao.DaoFactory;
import ca.cencol.geochat.dao.RoomDao;
import ca.cencol.geochat.mapper.ForbiddenException;
import ca.cencol.geochat.mapper.NotFoundException;
import ca.cencol.geochat.model.ChatRoom;
import ca.cencol.geochat.model.Message;
import ca.cencol.geochat.service.RoomService;

public class RoomServiceImpl implements RoomService {

  private static final RoomServiceImpl INSTANCE = new RoomServiceImpl();
  private final RoomDao rooms = DaoFactory.createRoomDao();

  /**
   * Stores userId->roomId mapping
   */
  private final Map<String, String> roomsByUsers = Maps.newHashMap();

  @Override
  public Collection<Message> getMessages(String userId, String roomId) {

    // return all messages for the user in the room from the "beginning of time"
    return getMessages(userId, roomId, new Date(0));
  }

  @Override
  public Collection<Message> getMessages(String userId, String roomId, Date lastTime) {
    checkState(!isNullOrEmpty(roomId), "Null or empty roomId");

    if (!isCurrentRoom(userId, roomId)) {
      throw new NotFoundException(format("User %s is not registered in room %s", userId, roomId));
    }

    // Get messages for user
    ChatRoom room = checkAndCreateChatRoom(roomId);

    return getMessagesAfterTime(room.getMessages(), lastTime);
  }

  private Collection<Message> getMessagesAfterTime(Collection<Message> messages, Date time) {
    List<Message> filteredMessages = Lists.newArrayList();

    for (Message msg : messages) {
      if (msg.getTime().after(time)) {
        filteredMessages.add(msg);
      }
    }
    return filteredMessages;
  }

  private ChatRoom checkAndCreateChatRoom(String locationId) {
    ChatRoom room = rooms.getRoom(locationId);
    if (room == null) {
      room = new ChatRoom(locationId);
      rooms.addRoom(room);
    }

    return room;
  }

  @Override
  public String getUserRoom(String userId) {
    checkState(!isNullOrEmpty(userId), "userId can't be null or empty");

    return roomsByUsers.get(userId);
  }

  @Override
  public void postMessage(String userId, String roomId, String message) {
    checkState(!isNullOrEmpty(userId) && !isNullOrEmpty(roomId) && !isNullOrEmpty(message),
        "One of the arguments is null or empty");

    if (!isCurrentRoom(userId, roomId)) {
      throw new ForbiddenException(format("%s tried to post wrong room %s", userId, roomId));
    }

    ChatRoom room = rooms.getRoom(roomId);
    room.postMessage(userId, message);
  }

  @Override
  public void addUserToRoom(String userId, String roomId) {
    checkState(!isNullOrEmpty(userId) && !isNullOrEmpty(roomId), "userId and roomId can't be null or empty");
    removeUserFromRoom(userId);
    checkAndCreateChatRoom(roomId);
    roomsByUsers.put(userId, roomId);
  }

  @Override
  public void removeUserFromRoom(String userId) {
    checkState(!isNullOrEmpty(userId), "userId can't be null or empty");
    roomsByUsers.remove(userId);
  }

  @Override
  public boolean isCurrentRoom(String userId, String roomId) {
    String userRoom = getUserRoom(userId);
    if (userRoom == null || !userRoom.equals(roomId)) {
      return false;
    }

    return true;
  }

  public static RoomServiceImpl getInstance() {
    return INSTANCE;
  }

}
