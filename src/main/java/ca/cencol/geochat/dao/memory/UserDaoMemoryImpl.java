package ca.cencol.geochat.dao.memory;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import com.google.common.collect.Maps;

import ca.cencol.geochat.dao.UserDao;
import ca.cencol.geochat.model.User;

/**
 * An in-memory implementation of the {@link UserDao}.<br>
 * <b>Deprecated:</b> Use MySQL instance instead.
 */
@Deprecated
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDaoMemoryImpl implements UserDao {

  private static final UserDaoMemoryImpl INSTANCE = new UserDaoMemoryImpl();
  private final Map<String, User> users = Maps.newHashMap();

  @Override
  public void addUser(User user) {
    checkState(user != null, "User can't be null");
    String userId = user.getUserId();
    checkState(!isNullOrEmpty(userId), "userId can't be null or empry");
    users.put(userId, user);
  }

  @Override
  public User getUser(String userId) {
    checkState(!isNullOrEmpty(userId), "userId can't be null or empty");

    return users.get(userId);
  }

  public static UserDao getInstance() {
    return INSTANCE;
  }

  @Override
  public int countByUsername(@NonNull String username) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int countByEmail(@NonNull String email) {
    throw new UnsupportedOperationException();
  }

}
