package ca.cencol.geochat.service;

import ca.cencol.geochat.service.impl.RoomServiceImpl;
import ca.cencol.geochat.service.impl.UsersServiceImpl;


/**
 * Factory for services creation.
 */
public final class ServiceFactory {
  
  private ServiceFactory() {
  }
  
  public static RoomService createRoomService() {
    return new RoomServiceImpl();
  }
  
  public static UsersService createUsersService() {
    return new UsersServiceImpl();
  }

}
