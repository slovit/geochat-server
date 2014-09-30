package ca.cencol.geochat.dao;

import ca.cencol.geochat.model.User;

public interface UserDao {

  void addUser(User user);

  User getUser(String userId);

}
