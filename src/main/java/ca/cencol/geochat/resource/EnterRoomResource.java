package ca.cencol.geochat.resource;

import static java.lang.String.format;

import java.util.Date;

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
import ca.cencol.geochat.model.PullRequestRecord;
import ca.cencol.geochat.service.PullRequestHistoryService;
import ca.cencol.geochat.service.RoomService;
import ca.cencol.geochat.service.ServiceFactory;
import ca.cencol.geochat.service.UsersService;

@Path("/enter")
@Api(value = "/enter", description = "Enter into the chat room")
@Produces(MediaType.APPLICATION_JSON)
public class EnterRoomResource {

  private final RoomService roomService = ServiceFactory.createRoomService();
  private final PullRequestHistoryService requestHistoryService = ServiceFactory.createPullRequestHistoryService();
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
    
    PullRequestRecord record = requestHistoryService.getPullRequestRecord(userId);

    // update pull request history for the user
    requestHistoryService.updatePullRequestRecord(userId, new Date());

    boolean isNewToRoom = false;
    // check if user already assigned to some other room.
    if (!roomService.isCurrentRoom(userId, roomId)) {
      roomService.removeUserFromRoom(userId);
      roomService.addUserToRoom(userId, roomId);
      isNewToRoom = true;
    }

    if (record == null || isNewToRoom) {
      // return all the messages in the room
      return new EnterRoomResponse(roomId, roomService.getMessages(userId, roomId));
    }

    // return all the message after the last pull request
    return new EnterRoomResponse(roomId, roomService.getMessages(userId, roomId, record.getTime()));

  }
}
