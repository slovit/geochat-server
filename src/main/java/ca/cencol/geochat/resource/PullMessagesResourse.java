package ca.cencol.geochat.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ca.cencol.geochat.model.EnterRoomResponse;
import ca.cencol.geochat.service.RoomService;
import ca.cencol.geochat.service.ServiceFactory;

@Path("/get")
public class PullMessagesResourse {

  private final RoomService roomService = ServiceFactory.createRoomService();

  @GET
  @Path("{userId}/{locationId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getMessages(
      @PathParam("userId") String userId,
      @PathParam("locationId") String roomId) {
    
    // check if user already assigned to some other room.
    if (!roomService.isCurrentRoom(userId, roomId)) {
      return Response.status(Status.FORBIDDEN).build();
    }
    
    return Response.ok().entity(new EnterRoomResponse(roomId, roomService.getMessages(userId, roomId))).build();
  }
}
