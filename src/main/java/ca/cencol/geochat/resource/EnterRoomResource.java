package ca.cencol.geochat.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import ca.cencol.geochat.model.EnterRoomResponse;
import ca.cencol.geochat.service.RoomService;
import ca.cencol.geochat.service.ServiceFactory;

@Path("/enter")
@Api(value = "/enter", description = "Enter room service")
@Produces(MediaType.APPLICATION_JSON)
public class EnterRoomResource {

  private final RoomService roomService = ServiceFactory.createRoomService();

  @GET
  @ApiOperation(httpMethod = "GET", value = "Enter the room", notes = "Enter the room and fetch all messages from the room",
      response = EnterRoomResponse.class)
  @Path("/{userId}/{locationId}")
  public EnterRoomResponse getMessages(
      @ApiParam(value = "Unique user identifier", required = true) @PathParam("userId") String userId,
      @ApiParam(value = "Current user location", required = true) @PathParam("locationId") String roomId) {

    // check if user already assigned to some other room.
    if (!roomService.isCurrentRoom(userId, roomId)) {
      roomService.removeUserFromRoom(userId);
      roomService.addUserToRoom(userId, roomId);
    }

    return new EnterRoomResponse(roomId, roomService.getMessages(userId, roomId));
  }

}
