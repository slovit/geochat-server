package ca.cencol.geochat;

import static org.junit.Assert.*;
import lombok.val;

import org.junit.Test;

import ca.cencol.geochat.model.RegistrationUser;
import ca.cencol.geochat.model.User;
import ca.cencol.geochat.service.UsersService;
import ca.cencol.geochat.service.impl.UsersServiceImpl;

public class UsersServiceImplTest {

  @Test
  public void testRegisterAndGetUser() {
    UsersService service = UsersServiceImpl.getInstance();
    String userId = "user_register_test";
    String nickname = "nickname_register_test";
    User user = service.getUser(userId);
    assertNull(user);
    // register the user
    val registrationInfo = new RegistrationUser();
    registrationInfo.setUsername(nickname);
    service.registerUser(registrationInfo);
    user = service.getUser(userId);
    assertNotNull(user);
    assertEquals(nickname, user.getUsername());
  }

  @Test
  public void testGetInstance() {
    UsersService instance1 = UsersServiceImpl.getInstance();
    assertNotNull(instance1);
    UsersService instance2 = UsersServiceImpl.getInstance();
    assertEquals(instance1, instance2);
  }

}
