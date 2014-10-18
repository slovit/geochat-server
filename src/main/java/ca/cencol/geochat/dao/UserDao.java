package ca.cencol.geochat.dao;

import ca.cencol.geochat.model.User;

public interface UserDao {

  /**
   * Persists {@code user}
   */
  void addUser(User user);

  
  /**
   * Retrieves {@link User}
   * 
   * @return null if user was not found
   */
  User getById(String userId);
  
  /**
   * Finds a {@link User} by {@code email}.
   * 
   * @return null if user was not found
   */
  User getByEmail(String email);
  
  /**
   * Returns number of registered users with {@code username}
   */
  int countByUsername(String username);
  
  /**
   * Returns number of registered users with {@code email}
   */
  int countByEmail(String email);

}
