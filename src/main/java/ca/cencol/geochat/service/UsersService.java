package ca.cencol.geochat.service;

import ca.cencol.geochat.model.LoginRequest;
import ca.cencol.geochat.model.RegistrationUser;
import ca.cencol.geochat.model.UpdateUser;
import ca.cencol.geochat.model.User;

/**
 * A placeholder service for {@link User} registration.
 */
public interface UsersService {

  /**
   * Creates a new {@link User} and persists it.
   */
  String registerUser(RegistrationUser user);

  /**
   * Retrieve {@link User} by {@code userId}
   */
  User getUser(String userId);
  
  /**
   * Checks if {@code userId} is registered with the system.
   */
  boolean isRegistered(String userId);
  
  /**
   * Logins {@link User}
   */
  String loginUser(LoginRequest user);
  
  /**
   * Updates a {@link User} and persists it.
   */
  void updateUser(UpdateUser user);

}
