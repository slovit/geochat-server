package ca.cencol.geochat.service.impl;

import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;

import java.util.UUID;

import lombok.NonNull;
import lombok.val;
import ca.cencol.geochat.dao.DaoFactory;
import ca.cencol.geochat.dao.UserDao;
import ca.cencol.geochat.mapper.BadRequestException;
import ca.cencol.geochat.model.RegistrationUser;
import ca.cencol.geochat.model.User;
import ca.cencol.geochat.service.UsersService;

/**
 * An in-memory implementation of {@link UsersService}
 */
public class UsersServiceImpl implements UsersService {

  private static final UsersServiceImpl INSTANCE = new UsersServiceImpl();
  private final UserDao USER_DAO = DaoFactory.createUserDao();

  @Override
  public String registerUser(@NonNull RegistrationUser user) {
    checkUsername(user.getUsername());
    checkEmail(user.getEmail());
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
    checkRegistrationState(password != null, "Missing password field");
  }

  private void checkEmail(String email) {
    checkRegistrationState(email != null, "Missing email field");
    checkEmailUniqueness(email);
  }

  private void checkUsername(String username) {
    checkRegistrationState(username != null, "Missing username field");
    checkUsernameUniqueness(username);
  }

  private void checkUsernameUniqueness(@NonNull String username) {
    checkRegistrationState(!username.isEmpty(), "Username can't be empty");
    checkRegistrationState(USER_DAO.countByUsername(username) == 0,
        format("Username %s is already registered", username));
  }

  private void checkEmailUniqueness(@NonNull String email) {
    checkRegistrationState(!email.isEmpty(), "Email can't be empty");
    checkRegistrationState(USER_DAO.countByEmail(email) == 0,
        format("Email %s is already used for registration", email));
  }

  @Override
  public User getUser(@NonNull String userId) {
    checkState(!userId.isEmpty(), "userId can't be empty");

    return USER_DAO.getUser(userId);
  }

  public static UsersServiceImpl getInstance() {
    return INSTANCE;
  }

  private static void checkRegistrationState(boolean expression, String message) {
    if (!expression) {
      throw new BadRequestException(message);
    }
  }

}
