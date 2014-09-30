package ca.cencol.geochat.dao;

import ca.cencol.geochat.model.ChatRoom;

public interface RoomDao {

  void addRoom(ChatRoom roomId);
  
  ChatRoom getRoom(String roomId);
  
}
