package ca.cencol.geochat.service.impl;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Date;

import ca.cencol.geochat.dao.DaoFactory;
import ca.cencol.geochat.dao.PullRequestRecordDao;
import ca.cencol.geochat.model.PullRequestRecord;
import ca.cencol.geochat.service.PullRequestHistoryService;

public class PullRequestHistoryServiceImpl implements PullRequestHistoryService {

  private static final PullRequestHistoryServiceImpl INSTANCE = new PullRequestHistoryServiceImpl();
  private final PullRequestRecordDao pullRequestDao = DaoFactory.createPullRequestRecordDao();

  private PullRequestHistoryServiceImpl() {
  }

  @Override
  public void updatePullRequestRecord(String userId, Date time) {
    checkState(!isNullOrEmpty(userId) && (time != null));

    PullRequestRecord record = new PullRequestRecord(userId, time);
    pullRequestDao.addRecord(record);
  }

  @Override
  public PullRequestRecord getPullRequestRecord(String userId) {
    checkState(!isNullOrEmpty(userId), "userId can't be null or empty");

    return pullRequestDao.getRecord(userId);
  }

  @Override
  public void resetPullRequestHistory(String userId) {
    pullRequestDao.deleteRecord(userId);
  }

  public static PullRequestHistoryServiceImpl getInstance() {
    return INSTANCE;
  }
}
