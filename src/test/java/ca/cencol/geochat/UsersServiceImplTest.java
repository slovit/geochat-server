package ca.cencol.geochat;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import lombok.val;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.cencol.geochat.dao.UserDao;
import ca.cencol.geochat.mapper.BadRequestException;
import ca.cencol.geochat.mapper.ForbiddenException;
import ca.cencol.geochat.model.LoginRequest;
import ca.cencol.geochat.model.RegistrationUser;
import ca.cencol.geochat.model.User;
import ca.cencol.geochat.service.UsersService;
import ca.cencol.geochat.service.impl.UsersServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UsersServiceImplTest {
  
  @Mock
  UserDao userDao;
  UsersServiceImpl service;
  
  @Before
  public void setUp() {
    service = UsersServiceImpl.getTestInstance();
    service.setUserDao(userDao);
  }

  @Test
  @Ignore
  public void testRegisterAndGetUser() {
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
    UsersService instance1 = UsersServiceImpl.getTestInstance();
    assertNotNull(instance1);
    UsersService instance2 = UsersServiceImpl.getTestInstance();
    assertEquals(instance1, instance2);
  }
  
  @Test
  public void registerUserTest() {
    val registrationInfo = new RegistrationUser();
    // missing fields
    catchException(service).registerUser(registrationInfo);
    assertThat(caughtException()).isInstanceOf(BadRequestException.class);
    
    // empty username
    registrationInfo.setUsername("");
    catchException(service).registerUser(registrationInfo);
    assertThat(caughtException()).isInstanceOf(BadRequestException.class);
    
    // missing email/password
    val username = "t";
    registrationInfo.setUsername(username);
    catchException(service).registerUser(registrationInfo);
    assertThat(caughtException()).isInstanceOf(BadRequestException.class);
    
    // empty email
    registrationInfo.setEmail("");
    catchException(service).registerUser(registrationInfo);
    assertThat(caughtException()).isInstanceOf(BadRequestException.class);
    
    // missing password
    val email = "test@t.org";
    registrationInfo.setEmail(email);
    catchException(service).registerUser(registrationInfo);
    assertThat(caughtException()).isInstanceOf(BadRequestException.class);
    
    // empty password
    registrationInfo.setPassword("");
    catchException(service).registerUser(registrationInfo);
    assertThat(caughtException()).isInstanceOf(BadRequestException.class);
    
    registrationInfo.setPassword("p");
    
    // non-unique username
    reset(userDao);
    when(userDao.countByUsername(username)).thenReturn(1);
    catchException(service).registerUser(registrationInfo);
    assertThat(caughtException()).isInstanceOf(BadRequestException.class);
    verify(userDao).countByUsername(username);
    
    // non-unique email
    reset(userDao);
    when(userDao.countByEmail(email)).thenReturn(1);
    catchException(service).registerUser(registrationInfo);
    assertThat(caughtException()).isInstanceOf(BadRequestException.class);
    verify(userDao).countByEmail(email);
    
    // successful registration
    when(userDao.countByUsername(username)).thenReturn(0);
    when(userDao.countByEmail(email)).thenReturn(0);
    val userId = service.registerUser(registrationInfo);
    assertThat(userId).isNotNull();
    assertThat(userId).isNotEmpty();

    // check that UUID was used for generation
    UUID.fromString(userId);
  }

  @Test
  public void getUserTest() {
    val user = createTestUser();
    val userId = user.getUserId();
    
    assertThat(service.getUser(userId)).isNull();
    
    reset(userDao);
    when(userDao.getById(userId)).thenReturn(user);
    val fetchedUser = service.getUser(userId);
    assertThat(fetchedUser).isEqualTo(user);
    verify(userDao).getById(userId);
  }
  
  @Test
  public void isRegisteredTest() {
    val user = createTestUser();
    val userId = user.getUserId();
    
    assertThat(service.isRegistered(userId)).isFalse();
    when(userDao.getById(userId)).thenReturn(user);
    assertThat(service.isRegistered(userId)).isTrue();
  }
  
  @Test
  public void loginUserTest() {
    val user = createTestUser();
    val loginInfo = new LoginRequest();
    loginInfo.setEmail(user.getEmail());
    loginInfo.setPassword("pass");
    
    // user not found
    catchException(service).loginUser(loginInfo);
    assertThat(caughtException()).isInstanceOf(ForbiddenException.class);
    
    when(userDao.getByEmail(loginInfo.getEmail())).thenReturn(user);
    assertThat(service.loginUser(loginInfo)).isEqualTo(user.getUserId());
  }
  
  private static User createTestUser() {
    return User.builder()
        .userId("123")
        .username("username")
        .email("test@test.org")
        .password("pass")
        .build();
  }
  
}
