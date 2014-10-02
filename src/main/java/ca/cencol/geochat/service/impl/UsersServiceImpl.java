package ca.cencol.geochat.service.impl;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;
import ca.cencol.geochat.dao.DaoFactory;
import ca.cencol.geochat.dao.UserDao;
import ca.cencol.geochat.model.User;
import ca.cencol.geochat.service.UsersService;


/**
 * An in-memory implementation of {@link UsersService}
 */
public class UsersServiceImpl implements UsersService {
  
  private static final UsersServiceImpl INSTANCE = new UsersServiceImpl();
  private final UserDao USER_DAO = DaoFactory.createUserDao();
  
  @Override
  public void registerUser(String userId, String nickname) {
    checkState(!isNullOrEmpty(userId) && !isNullOrEmpty(nickname));
    
    User user = new User(userId, nickname);
    USER_DAO.addUser(user);
  }
  
  @Override
  public User getUser(String userId) {
    checkState(!isNullOrEmpty(userId));
    
    return USER_DAO.getUser(userId);
  }
  
  public static UsersServiceImpl getInstance() {
    return INSTANCE;
  }
}
