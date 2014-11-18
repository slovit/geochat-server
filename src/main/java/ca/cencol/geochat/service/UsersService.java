package ca.cencol.geochat.service;

import ca.cencol.geochat.model.LoginRequest;
import ca.cencol.geochat.model.RegistrationUser;
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
   * Updates additional info for the given {@code userId} and persists it.
   */
  void updateAdditionalInfo(String userId, String addInfo);
  
  /**
   * Updates image ID for the given {@code userId} and persists it.
   */
  void updateImageId(String userId, String imageId);

}
