package ca.cencol.geochat.dao.mysql;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import lombok.SneakyThrows;

class ConnectionManager {
  
  private static final ConnectionManager INSTANCE = new ConnectionManager();
  
  private final DataSource dataSource;
  
  @SneakyThrows
  private ConnectionManager() {
    Context ctx = new InitialContext();
    Context envCtx = (Context) ctx.lookup("java:comp/env");
    dataSource = (DataSource) envCtx.lookup("jdbc/MySQLDS");
  }
  
  @SneakyThrows
  public Connection getConnection() {
    return dataSource.getConnection();
  }
  
  public static ConnectionManager getInstance() {
    return INSTANCE;
  }

}
