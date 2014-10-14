package ca.cencol.geochat.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PullRequestRecordDaoMemoryTest.class, RoomDaoMemoryTest.class, UserDaoMemoryTest.class })
public class AllTests {

}
