package ca.cencol.geochat;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import ca.cencol.geochat.dao.PullRequestRecordDao;
import ca.cencol.geochat.dao.memory.PullRequestRecordDaoMemoryImpl;
import ca.cencol.geochat.model.PullRequestRecord;

public class PullRequestRecordDaoMemoryTest {

  @Test
  public void testAddRecord() {
    PullRequestRecordDao pullReqRecordDao = PullRequestRecordDaoMemoryImpl.getInstance();
    String userId = "user_1";
    PullRequestRecord rec = pullReqRecordDao.getRecord(userId);
    assertNull(rec);
    pullReqRecordDao.addRecord(new PullRequestRecord(userId, new Date()));
    rec = pullReqRecordDao.getRecord(userId);
    assertNotNull(rec);
    assertEquals(userId, rec.getUserId());
  }

  @Test(expected = IllegalStateException.class)
  public void testAddRecordIllegalArgument() {
    PullRequestRecordDao pullReqRecordDao = PullRequestRecordDaoMemoryImpl.getInstance();
    pullReqRecordDao.addRecord(null);
  }

  @Test
  public void testDeleteRecord() {
    PullRequestRecordDao pullReqRecordDao = PullRequestRecordDaoMemoryImpl.getInstance();
    String userId = "user_2";
    pullReqRecordDao.addRecord(new PullRequestRecord(userId, new Date()));
    pullReqRecordDao.deleteRecord(userId);
    PullRequestRecord rec = pullReqRecordDao.getRecord(userId);
    assertNull(rec);
  }

  @Test(expected = IllegalStateException.class)
  public void testDeleteRecordIllegalArgument() {
    PullRequestRecordDao pullReqRecordDao = PullRequestRecordDaoMemoryImpl.getInstance();
    pullReqRecordDao.deleteRecord(null);
  }

  @Test
  public void testGetRecord() {
    PullRequestRecordDao pullReqRecordDao = PullRequestRecordDaoMemoryImpl.getInstance();
    String userId = "user_3";
    PullRequestRecord rec = pullReqRecordDao.getRecord(userId);
    assertNull(rec);
    pullReqRecordDao.addRecord(new PullRequestRecord(userId, new Date()));
    rec = pullReqRecordDao.getRecord(userId);
    assertNotNull(rec);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetRecordIllegalArgument() {
    PullRequestRecordDao pullReqRecordDao = PullRequestRecordDaoMemoryImpl.getInstance();
    pullReqRecordDao.getRecord(null);
  }

  @Test
  public void testGetInstance() {
    PullRequestRecordDao pullReqRecordDao = PullRequestRecordDaoMemoryImpl.getInstance();
    assertNotNull(pullReqRecordDao);
    PullRequestRecordDao pullReqRecordDao2 = PullRequestRecordDaoMemoryImpl.getInstance();
    assertEquals(pullReqRecordDao, pullReqRecordDao2);
  }

}
