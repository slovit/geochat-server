package ca.cencol.geochat.dao;

import ca.cencol.geochat.model.PullRequestRecord;

public interface PullRequestRecordDao {

  /**
   * Adds a {@link PullRequestRecord} and persists it.
   */
  void addRecord(PullRequestRecord record);

  /**
   * Returns a {@link PullRequestRecord} for the provided {@code userId}.
   */
  PullRequestRecord getRecord(String userId);

  /**
   * Deletes a {@link PullRequestRecord} for the provided {@code userId}.
   */
  void deleteRecord(String userId);
}
