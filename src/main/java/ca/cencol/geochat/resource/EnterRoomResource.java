package ca.cencol.geochat.resource;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.cencol.geochat.model.EnterRoomResponse;
import ca.cencol.geochat.model.PullRequestRecord;
import ca.cencol.geochat.service.PullRequestHistoryService;
import ca.cencol.geochat.service.RoomService;
import ca.cencol.geochat.service.ServiceFactory;

@Path("/enter")
public class EnterRoomResource {

  private final RoomService roomService = ServiceFactory.createRoomService();
  private final PullRequestHistoryService requestHistoryService = ServiceFactory.createPullRequestHistoryService();

  @GET
  @Path("{userId}/{locationId}")
  @Produces(MediaType.APPLICATION_JSON)
  public EnterRoomResponse getMessages(
      @PathParam("userId") String userId,
      @PathParam("locationId") String roomId) {

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
