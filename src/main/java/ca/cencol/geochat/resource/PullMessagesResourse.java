package ca.cencol.geochat.resource;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import ca.cencol.geochat.model.EnterRoomResponse;
import ca.cencol.geochat.model.PullRequestRecord;
import ca.cencol.geochat.service.PullRequestHistoryService;
import ca.cencol.geochat.service.RoomService;
import ca.cencol.geochat.service.ServiceFactory;

@Path("/get")
@Api(value = "/enter", description = "Pull messages service")
@Produces(MediaType.APPLICATION_JSON)
public class PullMessagesResourse {

  private final RoomService roomService = ServiceFactory.createRoomService();
  private final PullRequestHistoryService requestHistoryService = ServiceFactory.createPullRequestHistoryService();

  @GET
  @ApiOperation(httpMethod = "GET", value = "Get messages", notes = "Fetch new messages from the room",
        response = Response.class)
  @Path("{userId}/{locationId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getMessages(
      @ApiParam(value = "Unique user identifier", required = true) @PathParam("userId") String userId,
      @ApiParam(value = "Current user location", required = true) @PathParam("locationId") String roomId) {

    // check if user already assigned to some other room.
    if (!roomService.isCurrentRoom(userId, roomId)) {
      return Response.status(Status.FORBIDDEN).build();
    }
    PullRequestRecord record = requestHistoryService.getPullRequestRecord(userId);
    // update pull request history for the user
    requestHistoryService.updatePullRequestRecord(userId, new Date());

    if (record == null) {
      // if no record exists for the given user id, return all the messages
      return Response.ok().entity(new EnterRoomResponse(roomId, roomService.getMessages(userId, roomId))).build();
    }
    // return all the messages posted after the last pull request time
    return Response.ok()
        .entity(new EnterRoomResponse(roomId, roomService.getMessages(userId, roomId, record.getTime()))).build();
  }
}
