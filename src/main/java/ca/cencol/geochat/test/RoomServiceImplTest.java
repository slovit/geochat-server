package ca.cencol.geochat.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import ca.cencol.geochat.mapper.ForbiddenException;
import ca.cencol.geochat.mapper.NotFoundException;
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
  public void testGetMessagesException() {
    roomService.getMessages(user1Room1, roomId2);
  }

  @Test
  public void testGetUserRoom() {
    assertEquals(roomId1, roomService.getUserRoom(user1Room1));
    assertEquals(null, roomService.getUserRoom("non_existent_user"));
  }

  @Test
  public void testPostMessage() {
    String uid = "userPost";
    String roomId = "roomPostId";
    roomService.addUserToRoom(uid, roomId);
    List<Message> msgList = roomService.getMessages(uid, roomId);
    int msgNumBefore = msgList.size();
    roomService.postMessage(uid, roomId, "xoxo");
    msgList = roomService.getMessages(uid, roomId);
    int msgNumAfter = msgList.size();
    assertEquals(msgNumBefore + 1, msgNumAfter);
  }

  @Test(expected = ForbiddenException.class)
  public void testPostMessageException() {
    roomService.postMessage(user3Room2, roomId1, "xoxo");
  }

  @Test
  public void testAddAndRemoveUserToRoom() {
    String uid = "userAddToRoom";
    String roomId = "roomAdd";
    boolean thrown = false;
    try {
      roomService.postMessage(uid, roomId, "xoxo");
    } catch (ForbiddenException e) {
      thrown = true;
    }
    assertTrue(thrown);
    roomService.addUserToRoom(uid, roomId);
    roomService.postMessage(uid, roomId, "xoxo");
  }

  @Test
  public void testIsCurrentRoom() {
    assertTrue(roomService.isCurrentRoom(user1Room1, roomId1));
    assertFalse(roomService.isCurrentRoom(user1Room1, roomId2));
  }

  @Test
  public void testGetInstance() {
    RoomService instance1 = RoomServiceImpl.getInstance();
    RoomService instance2 = RoomServiceImpl.getInstance();
    assertNotNull(instance1);
    assertEquals(instance1, instance2);
  }

}
