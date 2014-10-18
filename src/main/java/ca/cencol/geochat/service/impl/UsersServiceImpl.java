package ca.cencol.geochat.service.impl;

import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;

import java.util.UUID;

import lombok.NonNull;
import lombok.val;
import lombok.extern.slf4j.Slf4j;
import ca.cencol.geochat.dao.DaoFactory;
import ca.cencol.geochat.dao.UserDao;
import ca.cencol.geochat.mapper.BadRequestException;
import ca.cencol.geochat.mapper.ForbiddenException;
import ca.cencol.geochat.model.LoginRequest;
import ca.cencol.geochat.model.RegistrationUser;
import ca.cencol.geochat.model.User;
import ca.cencol.geochat.service.UsersService;

/**
 * An in-memory implementation of {@link UsersService}
 */
@Slf4j
public class UsersServiceImpl implements UsersService {

  private static final UsersServiceImpl INSTANCE = new UsersServiceImpl();
  private final UserDao USER_DAO = DaoFactory.createUserDao();

  @Override
  public String registerUser(@NonNull RegistrationUser user) {
    log.info("Registering user {}", user);
    checkUsername(user.getUsername());
    checkUsernameUniqueness(user.getUsername());
    
    checkEmail(user.getEmail());
    checkEmailUniqueness(user.getEmail());
    
    checkPassword(user.getPassword());
    

    val userId = UUID.randomUUID().toString();
    val newUser = User.builder()
        .userId(userId)
        .username(user.getUsername())
        .email(user.getEmail())
        .password(user.getPassword())
        .build();

    USER_DAO.addUser(newUser);

    return userId;
  }

  private void checkPassword(String password) {
    checkUserState(password != null, "Missing password field");
    checkUserState(!password.isEmpty(), "Password can't be empty");
  }

  private void checkEmail(String email) {
    checkUserState(email != null, "Missing email field");
    checkUserState(!email.isEmpty(), "Email can't be empty");
  }

  private void checkUsername(String username) {
    checkUserState(username != null, "Missing username field");
    checkUserState(!username.isEmpty(), "Username can't be empty");
  }

  private void checkUsernameUniqueness(@NonNull String username) {
    checkUserState(USER_DAO.countByUsername(username) == 0,
        format("Username %s is already registered", username));
  }

  private void checkEmailUniqueness(@NonNull String email) {
    checkUserState(USER_DAO.countByEmail(email) == 0,
        format("Email %s is already used for registration", email));
  }

  @Override
  public User getUser(@NonNull String userId) {
    checkState(!userId.isEmpty(), "userId can't be empty");

    return USER_DAO.getById(userId);
  }

  public static UsersServiceImpl getInstance() {
    return INSTANCE;
  }

  private static void checkUserState(boolean expression, String message) {
    if (!expression) {
      log.info("{}", message);
      throw new BadRequestException(message);
    }
  }

  @Override
  public boolean isRegistered(@NonNull String userId) {
    checkState(!userId.isEmpty(), "userId can't be empty");
    val user = USER_DAO.getById(userId);
    
    return user == null ? false : true;
  }

  @Override
  public String loginUser(LoginRequest loginInfo) {
    val email = loginInfo.getEmail();
    log.info("Login request from user '{}'", email);
    checkEmail(email);
    checkPassword(loginInfo.getPassword());
    
    val user = USER_DAO.getByEmail(email);
    if (user == null) {
      denyLogin(format("Have not found user with email: '%s'", email));
    } else if (!isValidPassword(user, loginInfo)) {
      denyLogin(format("User '%s' entered invalid password", email));
    }
    
    return user.getUserId();
  }
  
  private static boolean isValidPassword(User user, LoginRequest loginInfo) {
    return user.getPassword().equals(loginInfo.getPassword());
  }
  
  private static void denyLogin(String logMessage) {
    log.info(logMessage);
    throw new ForbiddenException("Login credentials are incorrect");
  }

}
