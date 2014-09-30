package ca.cencol.geochat.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.cencol.geochat.model.EnterRoomResponse;
import ca.cencol.geochat.service.RoomService;
import ca.cencol.geochat.service.ServiceFactory;

@Path("/enter")
public class EnterRoomResource {
  
  private final RoomService roomService = ServiceFactory.createRoomService();

  @GET
  @Path("{userId}/{locationId}")
  @Produces(MediaType.APPLICATION_JSON)
  public EnterRoomResponse getMessages(
      @PathParam("userId") String userId,
      @PathParam("locationId") String roomId) {
    
    // check if user already assigned to some other room.
    if (!roomService.isCurrentRoom(userId, roomId)) {
      roomService.removeUserFromRoom(userId);
      roomService.addUserToRoom(userId, roomId);
    }
    
    return new EnterRoomResponse(roomId, roomService.getMessages(userId, roomId));
  }
  
}
