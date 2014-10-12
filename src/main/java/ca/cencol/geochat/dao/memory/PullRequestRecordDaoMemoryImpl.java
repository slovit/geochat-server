package ca.cencol.geochat.dao.memory;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Map;

import com.google.common.collect.Maps;

import ca.cencol.geochat.dao.PullRequestRecordDao;
import ca.cencol.geochat.model.PullRequestRecord;

/**
 * An in-memory implementation of the {@link PullRequestRecordDao}
 */
public class PullRequestRecordDaoMemoryImpl implements PullRequestRecordDao {

  private static final PullRequestRecordDaoMemoryImpl INSTANCE = new PullRequestRecordDaoMemoryImpl();

  private final Map<String, PullRequestRecord> recordsByUsers = Maps.newHashMap();

  private PullRequestRecordDaoMemoryImpl() {
  }

  @Override
  public void addRecord(PullRequestRecord record) {
    checkState(record != null, "Record can't be null");
    String userId = record.getUserId();
    checkState(!isNullOrEmpty(userId), "userId can't be null or empry");
    recordsByUsers.put(userId, record);
  }

  @Override
  public void deleteRecord(String userId) {
    checkState(!isNullOrEmpty(userId), "userId can't be null or empty");

    recordsByUsers.remove(userId);
  }

  @Override
  public PullRequestRecord getRecord(String userId) {
    checkState(!isNullOrEmpty(userId), "userId can't be null or empty");

    return recordsByUsers.get(userId);
  }

  public static PullRequestRecordDaoMemoryImpl getInstance() {
    return INSTANCE;
  }

}
