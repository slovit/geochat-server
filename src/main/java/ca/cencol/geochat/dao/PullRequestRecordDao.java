package ca.cencol.geochat.dao;

import ca.cencol.geochat.model.PullRequestRecord;

public interface PullRequestRecordDao {

  void addRecord(PullRequestRecord record);

  PullRequestRecord getRecord(String userId);

}
