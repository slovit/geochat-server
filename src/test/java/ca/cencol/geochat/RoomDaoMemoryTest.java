package ca.cencol.geochat;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.cencol.geochat.dao.RoomDao;
import ca.cencol.geochat.dao.memory.RoomDaoMemoryImpl;
import ca.cencol.geochat.model.ChatRoom;

public class RoomDaoMemoryTest {

  @Test
  public void testAddRoom() {
    RoomDao roomDao = RoomDaoMemoryImpl.getInstance();
    String roomId = "room_1";
    ChatRoom room = roomDao.getRoom(roomId);
    assertNull(room);
    roomDao.addRoom(new ChatRoom(roomId));
    room = roomDao.getRoom(roomId);
    assertNotNull(room);
  }

  @Test(expected = IllegalStateException.class)
  public void testAddRoomIllegalArgument() {
    RoomDao roomDao = RoomDaoMemoryImpl.getInstance();
    roomDao.addRoom(null);
  }

  @Test
  public void testGetRoom() {
    RoomDao roomDao = RoomDaoMemoryImpl.getInstance();
    String roomId = "room_2";
    ChatRoom room = roomDao.getRoom(roomId);
    assertNull(room);
    roomDao.addRoom(new ChatRoom(roomId));
    room = roomDao.getRoom(roomId);
    assertNotNull(room);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetRoomIllegalArgument() {
    RoomDao roomDao = RoomDaoMemoryImpl.getInstance();
    roomDao.getRoom(null);
  }

  @Test
  public void testGetInstance() {
    RoomDao roomDao = RoomDaoMemoryImpl.getInstance();
    RoomDao roomDao2 = RoomDaoMemoryImpl.getInstance();
    assertNotNull(roomDao);
    assertEquals(roomDao, roomDao2);
  }

}
