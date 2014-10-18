package ca.cencol.geochat.dao;

import ca.cencol.geochat.model.User;

public interface UserDao {

  /**
   * Persists {@code user}
   */
  void addUser(User user);

  
  /**
   * Retrieves {@link User}
   */
  User getUser(String userId);
  
  /**
   * Returns number of registered users with {@code username}
   */
  int countByUsername(String username);
  
  /**
   * Returns number of registered users with {@code email}
   */
  int countByEmail(String email);

}
