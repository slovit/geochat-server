package ca.cencol.geochat;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PullRequestHistoryServiceImplTest.class, PullRequestRecordDaoMemoryTest.class, RoomDaoMemoryTest.class, RoomServiceImplTest.class, UserDaoMemoryTest.class, UsersServiceImplTest.class })
public class AllTests {

}
