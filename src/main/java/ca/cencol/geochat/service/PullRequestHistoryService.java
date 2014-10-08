package ca.cencol.geochat.service;

import java.util.Date;

import ca.cencol.geochat.model.PullRequestRecord;

/**
 * The service is used to manage pull request history for each {@link User}
 */
public interface PullRequestHistoryService {

  /**
   * Updates (or creates) a new {@link PullRequestRecord} and persists it.
   */
  void updatePullRequestRecord(String userId, Date time);

  /**
   * Returns {@link PullRequestRecord} by {@code userId}
   */
  PullRequestRecord getPullRequestRecord(String userId);

  /**
   * Resets pull request history for the given {@code userId}.
   */
  void resetPullRequestHistory(String userId);

}
