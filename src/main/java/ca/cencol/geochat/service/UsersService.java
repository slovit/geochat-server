package ca.cencol.geochat.service;

import ca.cencol.geochat.model.User;

/**
 * A placeholder service for {@link User} registration.
 */
public interface UsersService {

  /**
   * Creates a new {@link User} and persists it.
   */
  void registerUser(String userId, String nickname);

  /**
   * Retrieve {@link User} by {@code userId}
   */
  User getUser(String userId);

}
