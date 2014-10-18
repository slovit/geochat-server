package ca.cencol.geochat.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.cencol.geochat.dao.UserDao;
import ca.cencol.geochat.dao.memory.UserDaoMemoryImpl;
import ca.cencol.geochat.model.User;

@SuppressWarnings("deprecation")
public class UserDaoMemoryTest {

  @Test
  public void testAddUser() {
    UserDao userDao = UserDaoMemoryImpl.getInstance();
    String uid = "user_1";
    User user = userDao.getUser(uid);
    assertNull(user);
    userDao.addUser(User.builder().userId(uid).username("nickname").build());
    user = userDao.getUser(uid);
    assertNotNull(user);
    assertEquals(uid, user.getUserId());
  }

  @Test(expected = IllegalStateException.class)
  public void testAddUserIllegalArgument() {
    UserDao userDao = UserDaoMemoryImpl.getInstance();
    userDao.addUser(null);
  }

  @Test
  public void testGetUser() {
    UserDao userDao = UserDaoMemoryImpl.getInstance();
    String uid = "user_2";
    User user1 = userDao.getUser(uid);
    assertNull(user1);
    userDao.addUser(User.builder().userId(uid).username("nickname2").build());
    User user2 = userDao.getUser(uid);
    assertNotNull(user2);
  }
  
  @Test(expected = IllegalStateException.class)
  public void testGetUserIllegalArgument() {
    UserDao userDao = UserDaoMemoryImpl.getInstance();
    userDao.getUser(null);
  }

  @Test
  public void testGetInstance() {
    UserDao userDao = UserDaoMemoryImpl.getInstance();
    UserDao userDao2 = UserDaoMemoryImpl.getInstance();
    assertNotNull(userDao);
    assertEquals(userDao, userDao2);
  }

}
