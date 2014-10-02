package ca.cencol.geochat.model;

import java.util.Date;

/**
 * The class is used to represent a record in the history of pull messages requests
 */
public class PullRequestRecord {

  private final String userId;
  private final Date time;

  public PullRequestRecord(String userId, Date time) {
    this.userId = userId;
    this.time = time;
  }

  public String getUserId() {
    return userId;
  }

  public Date getTime() {
    return time;
  }

  @Override
  public String toString() {
    return String.format("UserId: %s, Time: %", userId, time.toString());
  }
}
