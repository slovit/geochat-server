package ca.cencol.geochat.dao.mysql;

import static java.lang.String.format;
import static ca.cencol.geochat.dao.mysql.ColumnNames.User.*;

import java.sql.ResultSet;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import ca.cencol.geochat.dao.UserDao;
import ca.cencol.geochat.model.User;

// TODO: Close connections properly
public class UserDaoImpl implements UserDao {

  private static final UserDaoImpl INSTANCE = new UserDaoImpl();

  private static final String INSERT_USER = format(
      "insert into User (%s, %s, %s, %s, %s, %s) values (?, ?, ?, ?, ?, ?)",
      USER_ID, USERNAME, EMAIL, PASSWORD, IMAGE_ID, ADDITIONAL_INFO);
  private static final String SELECT_BY_ID = format("select * from User where %s = ?", USER_ID);
  private static final String SELECT_BY_EMAIL = format("select * from User where %s = ?", EMAIL);
  private static final String COUNT_BY_EMAIL = format("select count(*) as total from User where %s = ?", EMAIL);
  private static final String COUNT_BY_USERNAME = format("select count(*) as total from User where %s = ?", USERNAME);
  private static final String UPDATE_USER = format(
      "update User set %s = ?, %s = ?, %s = ?, %s = ?, %s = '?' where %s = ?",
      USERNAME, EMAIL, PASSWORD, IMAGE_ID, ADDITIONAL_INFO, USER_ID);

  private final ConnectionManager connectionManager = ConnectionManager.getInstance();

  @Override
  @SneakyThrows
  public void addUser(@NonNull User user) {
    val insertStmt = connectionManager.getConnection().prepareStatement(INSERT_USER);
    insertStmt.setString(1, user.getUserId());
    insertStmt.setString(2, user.getUsername());
    insertStmt.setString(3, user.getEmail());
    insertStmt.setString(4, user.getPassword());
    insertStmt.setString(5, user.getImageId());
    insertStmt.setString(6, user.getAdditionalInfo());

    insertStmt.executeUpdate();
  }

  @Override
  public User getById(@NonNull String userId) {
    return getUserByQuery(SELECT_BY_ID, userId);
  }

  @Override
  public User getByEmail(@NonNull String email) {
    return getUserByQuery(SELECT_BY_EMAIL, email);
  }

  @SneakyThrows
  private User getUserByQuery(String query, String searchParameter) {
    val selectStmt = connectionManager.getConnection().prepareStatement(query);
    selectStmt.setString(1, searchParameter);
    val resultSet = selectStmt.executeQuery();

    return createUser(resultSet);
  }

  @SneakyThrows
  private static User createUser(ResultSet resultSet) {
    val userBuilder = User.builder();

    while (resultSet.next()) {
      userBuilder.userId(resultSet.getString(USER_ID));
      userBuilder.username(resultSet.getString(USERNAME));
      userBuilder.email(resultSet.getString(EMAIL));
      userBuilder.password(resultSet.getString(PASSWORD));
      userBuilder.imageId(resultSet.getString(IMAGE_ID));
      userBuilder.additionalInfo(resultSet.getString(ADDITIONAL_INFO));

    }
    resultSet.close();
    val user = userBuilder.build();

    return user.isValid() ? user : null;
  }

  @Override
  @SneakyThrows
  public int countByUsername(@NonNull String username) {
    val stmt = connectionManager.getConnection().prepareStatement(COUNT_BY_USERNAME);
    stmt.setString(1, username);
    val resultSet = stmt.executeQuery();
    int count = -1;

    while (resultSet.next()) {
      count = resultSet.getInt("total");
    }
    resultSet.close();

    return count;
  }

  @Override
  @SneakyThrows
  public int countByEmail(@NonNull String email) {
    val stmt = connectionManager.getConnection().prepareStatement(COUNT_BY_EMAIL);
    stmt.setString(1, email);
    val resultSet = stmt.executeQuery();
    int count = -1;

    while (resultSet.next()) {
      count = resultSet.getInt("total");
    }
    resultSet.close();

    return count;
  }

  public static UserDao getInstance() {
    return INSTANCE;
  }

  @Override
  @SneakyThrows
  public void updateUser(@NonNull User user) {
    val insertStmt = connectionManager.getConnection().prepareStatement(UPDATE_USER);

    insertStmt.setString(1, user.getUsername());
    insertStmt.setString(2, user.getEmail());
    insertStmt.setString(3, user.getPassword());
    insertStmt.setString(4, user.getImageId());
    insertStmt.setString(5, user.getAdditionalInfo());
    insertStmt.setString(6, user.getUserId());

    insertStmt.executeUpdate();
  }

}
