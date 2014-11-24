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

import ca.cencol.geochat.mapper.ForbiddenException;
import ca.cencol.geochat.model.EnterRoomResponse;
import ca.cencol.geochat.service.RoomService;
import ca.cencol.geochat.service.ServiceFactory;
import ca.cencol.geochat.service.UsersService;

@Path("/enter")
@Api(value = "/enter", description = "Enter into the chat room")
@Produces(MediaType.APPLICATION_JSON)
public class EnterRoomResource {

  private final RoomService roomService = ServiceFactory.createRoomService();
  private final UsersService userService = ServiceFactory.createUsersService();

  @GET
  @ApiOperation(httpMethod = "GET", value = "Enter the chat room",
      notes = "Enter the chat room and fetch all messages from the room",
      response = EnterRoomResponse.class)
  @Path("/{userId}/{locationId}")
  @Produces(MediaType.APPLICATION_JSON)
  public EnterRoomResponse getMessages(
      @ApiParam(value = "Unique user identifier", required = true) @PathParam("userId") String userId,
      @ApiParam(value = "Current user location", required = true) @PathParam("locationId") String roomId) {

    if (!userService.isRegistered(userId)) {
      throw new ForbiddenException(format("User %s is not registered", userId));
    }

    // check if user already assigned to some other room.
    if (!roomService.isCurrentRoom(userId, roomId)) {
      roomService.removeUserFromRoom(userId);
      roomService.addUserToRoom(userId, roomId);
    }
    // return all the messages in the room
    return new EnterRoomResponse(roomId, roomService.getMessages(userId, roomId));

  }
}
