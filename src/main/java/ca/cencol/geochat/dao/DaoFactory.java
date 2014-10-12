package ca.cencol.geochat.dao;

import ca.cencol.geochat.dao.memory.RoomDaoMemoryImpl;
import ca.cencol.geochat.dao.memory.UserDaoMemoryImpl;
import ca.cencol.geochat.dao.memory.PullRequestRecordDaoMemoryImpl;


public final class DaoFactory {
  
  private DaoFactory() {
  }
  
  public static RoomDao createRoomDao() {
    return RoomDaoMemoryImpl.getInstance();
  }
  
  public static UserDao createUserDao() {
    return UserDaoMemoryImpl.getInstance();
  }
  
  public static PullRequestRecordDaoMemoryImpl createPullRequestRecordDao() {
    return PullRequestRecordDaoMemoryImpl.getInstance();
  }

}
