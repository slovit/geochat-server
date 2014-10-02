package ca.cencol.geochat.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import ca.cencol.geochat.model.IncommingMessage;
import ca.cencol.geochat.service.RoomService;
import ca.cencol.geochat.service.ServiceFactory;

@Path("/post")
@Api(value = "/post", description = "Post message to the chat room")
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

  private final RoomService roomService = ServiceFactory.createRoomService();

  @GET
  @ApiOperation(httpMethod = "GET", value = "Post a message", notes = "MUST NOT be used by the client. For testing only")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Message has been posted"),
      @ApiResponse(code = 403, message = "User tried to post to a chat room to which they are not assigned")})
  @Path("/{userId}/{locationId}/{message}")
  public Response post(
      @ApiParam(value = "Unique user identifier", required = true) @PathParam("userId") String userId,
      @ApiParam(value = "Current user location", required = true) @PathParam("locationId") String roomId,
      @ApiParam(value = "Message", required = true) @PathParam("message") String message) {

    roomService.postMessage(userId, roomId, message);

    return Response.ok().build();
  }

  @POST
  @ApiOperation(httpMethod = "POST", value = "Post a message", notes = "Posts message fetched from the request body")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Message has been posted"),
      @ApiResponse(code = 400, message = "Message can't be parsed"),
      @ApiResponse(code = 403, message = "User tried to post to a chat room to which they are not assigned")})
  @Path("/{userId}/{locationId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response postPost(
      @ApiParam(value = "Unique user identifier", required = true) @PathParam("userId") String userId,
      @ApiParam(value = "Current user location", required = true) @PathParam("locationId") String roomId,
      @ApiParam(value = "Message", required = true) IncommingMessage message) {

    roomService.postMessage(userId, roomId, message.toString());

    return Response.ok().build();
  }

}
