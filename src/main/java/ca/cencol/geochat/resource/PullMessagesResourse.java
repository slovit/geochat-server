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
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import ca.cencol.geochat.mapper.ForbiddenException;
import ca.cencol.geochat.model.EnterRoomResponse;
import ca.cencol.geochat.model.PullRequestRecord;
import ca.cencol.geochat.service.PullRequestHistoryService;
import ca.cencol.geochat.service.RoomService;
import ca.cencol.geochat.service.ServiceFactory;

@Path("/get")
@Api(value = "/get", description = "Pull messages service")
@Produces(MediaType.APPLICATION_JSON)
public class PullMessagesResourse {

  private final RoomService roomService = ServiceFactory.createRoomService();
  private final PullRequestHistoryService requestHistoryService = ServiceFactory.createPullRequestHistoryService();

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
    PullRequestRecord record = requestHistoryService.getPullRequestRecord(userId);
    // update pull request history for the user
    requestHistoryService.updatePullRequestRecord(userId, new Date());

    if (record == null) {
      // if no record exists for the given user id, return all the messages
      return new EnterRoomResponse(roomId, roomService.getMessages(userId, roomId));
    }
    // return all the messages posted after the last pull request time
    return new EnterRoomResponse(roomId, roomService.getMessages(userId, roomId, record.getTime()));
  }
}
