package ca.cencol.geochat.dao;

import ca.cencol.geochat.dao.memory.RoomDaoMemoryImpl;
import ca.cencol.geochat.dao.memory.PullRequestRecordDaoMemoryImpl;
import ca.cencol.geochat.dao.mysql.UserDaoImpl;


public final class DaoFactory {
  
  private DaoFactory() {
  }
  
  public static RoomDao createRoomDao() {
    return RoomDaoMemoryImpl.getInstance();
  }
  
  public static UserDao createUserDao() {
    return UserDaoImpl.getInstance();
  }
  
  public static PullRequestRecordDaoMemoryImpl createPullRequestRecordDao() {
    return PullRequestRecordDaoMemoryImpl.getInstance();
  }

}
