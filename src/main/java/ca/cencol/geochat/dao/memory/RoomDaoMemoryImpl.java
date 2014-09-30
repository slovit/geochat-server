package ca.cencol.geochat.dao.memory;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Map;

import com.google.common.collect.Maps;

import ca.cencol.geochat.dao.RoomDao;
import ca.cencol.geochat.model.ChatRoom;

public class RoomDaoMemoryImpl implements RoomDao {

  private static final RoomDaoMemoryImpl INSTANCE = new RoomDaoMemoryImpl();
  
  private final Map<String, ChatRoom> rooms = Maps.newHashMap();

  private RoomDaoMemoryImpl() {
  }

  @Override
  public void addRoom(ChatRoom room) {
    checkState(room != null, "Room can't be null");
    String roomId = room.getRoomId();
    checkState(!isNullOrEmpty(roomId), "roomId can't be null or empty");
    rooms.put(roomId, room);
  }

  @Override
  public ChatRoom getRoom(String roomId) {
    checkState(!isNullOrEmpty(roomId), "roomId can't be null or empty");

    return rooms.get(roomId);
  }

  public static RoomDao getInstance() {
    return INSTANCE;
  }

}
