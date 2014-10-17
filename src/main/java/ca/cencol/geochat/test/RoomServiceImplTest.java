package ca.cencol.geochat.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import ca.cencol.geochat.dao.RoomDao;
import ca.cencol.geochat.dao.memory.RoomDaoMemoryImpl;
import ca.cencol.geochat.mapper.NotFoundException;
import ca.cencol.geochat.model.ChatRoom;
import ca.cencol.geochat.model.Message;
import ca.cencol.geochat.service.RoomService;
import ca.cencol.geochat.service.impl.RoomServiceImpl;

public class RoomServiceImplTest {

  public static Date date1;
  public static Date date2;
  public static Date date3;

  public static String user1Room1 = "userId_1";
  public static String user2Room1 = "userId_2";
  public static String user3Room2 = "userId_3";

  public static String roomId1 = "room_1";
  public static String roomId2 = "room_2";

  public static RoomService roomService;

  static {
    try {
      roomService = RoomServiceImpl.getInstance();
      roomService.addUserToRoom(user1Room1, roomId1);
      roomService.addUserToRoom(user2Room1, roomId1);
      roomService.addUserToRoom(user3Room2, roomId2);
      date1 = new Date();

      Thread.sleep(5);

      roomService.postMessage(user1Room1, roomId1, "message1");
      date2 = new Date();

      Thread.sleep(5);

      roomService.postMessage(user1Room1, roomId1, "message2");
      date3 = new Date();
    } catch (InterruptedException e) {

      e.printStackTrace();
    }
  }

  @Test
  public void testGetMessages() {
    List<Message> msgList = roomService.getMessages(user1Room1, roomId1);
    assertEquals(2, msgList.size());
    msgList = roomService.getMessages(user1Room1, roomId1, date2);
    assertEquals(1, msgList.size());
    msgList = roomService.getMessages(user1Room1, roomId1, date3);
    assertEquals(0, msgList.size());
  }

  @Test(expected = NotFoundException.class)
  public void testGetMessagesNotRegistered() {
    roomService.getMessages(user1Room1, roomId2);

  }

  @Test
  public void testGetUserRoom() {
    assertEquals(roomId1, roomService.getUserRoom(user1Room1));
    assertEquals(null, roomService.getUserRoom("non_existent_user"));
  }

  @Test
  public void testPostMessage() {
    // TODO
  }

  @Test
  public void testAddUserToRoom() {
    // TODO
  }

  @Test
  public void testRemoveUserFromRoom() {
    // TODO
  }

  @Test
  public void testIsCurrentRoom() {
    // TODO
  }

  @Test
  public void testGetInstance() {
    // TODO
  }

}
