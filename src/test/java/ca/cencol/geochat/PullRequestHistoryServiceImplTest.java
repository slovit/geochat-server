package ca.cencol.geochat;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import ca.cencol.geochat.model.PullRequestRecord;
import ca.cencol.geochat.service.PullRequestHistoryService;
import ca.cencol.geochat.service.impl.PullRequestHistoryServiceImpl;

public class PullRequestHistoryServiceImplTest {

  private static PullRequestHistoryService service = PullRequestHistoryServiceImpl.getInstance();

  @Test
  public void testUpdatePullRequestRecord() {
    String userId = "user_update_history_test";
    Date time = new Date(1000);
    service.updatePullRequestRecord(userId, time);
    PullRequestRecord record = service.getPullRequestRecord(userId);
    assertNotNull(record);
    assertEquals(time, record.getTime());
  }

  @Test
  public void testGetPullRequestRecord() {
    String userId = "user_get_history_test";
    PullRequestRecord record = service.getPullRequestRecord(userId);
    assertNull(record);
    Date time = new Date();
    service.updatePullRequestRecord(userId, time);
    record = service.getPullRequestRecord(userId);
    assertNotNull(record);
    assertEquals(time, record.getTime());
  }

  @Test
  public void testResetPullRequestHistory() {
    String userId = "user_reset_history_test";
    PullRequestRecord record = service.getPullRequestRecord(userId);
    assertNull(record);
    Date time = new Date();
    service.updatePullRequestRecord(userId, time);
    record = service.getPullRequestRecord(userId);
    assertNotNull(record);
    service.resetPullRequestHistory(userId);
    record = service.getPullRequestRecord(userId);
    assertNull(record);
  }

  @Test
  public void testGetInstance() {
    PullRequestHistoryService instance1 = PullRequestHistoryServiceImpl.getInstance();
    assertNotNull(instance1);
    PullRequestHistoryService instance2 = PullRequestHistoryServiceImpl.getInstance();
    assertEquals(instance1, instance2);
  }

}
