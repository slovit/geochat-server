package ca.cencol.geochat.resource;

import static java.lang.String.format;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import ca.cencol.geochat.mapper.ForbiddenException;
import ca.cencol.geochat.model.EnterRoomResponse;
import ca.cencol.geochat.service.RoomService;
import ca.cencol.geochat.service.ServiceFactory;

@Path("/get")
@Api(value = "/get", description = "Pull messages service")
@Produces(MediaType.APPLICATION_JSON)
public class PullMessagesResourse {

  private final RoomService roomService = ServiceFactory.createRoomService();
  
  @GET
  @ApiOperation(httpMethod = "GET", value = "Get messages", notes = "Fetch new messages from the room",
      response = EnterRoomResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 403, message = "User does not belong to the room")})
  @Path("/{userId}/{locationId}")
  @Produces(MediaType.APPLICATION_JSON)
  public EnterRoomResponse getMessages(
      @ApiParam(value = "Unique user identifier", required = true) @PathParam("userId") String userId,
      @ApiParam(value = "Current user location", required = true) @PathParam("locationId") String roomId) {

    // check if user already assigned to some other room.
    if (!roomService.isCurrentRoom(userId, roomId)) {
      throw new ForbiddenException(format("User %s does not belong to room %s", userId, roomId));
    }

    return new EnterRoomResponse(roomId, roomService.getMessages(userId, roomId));
  }
}
