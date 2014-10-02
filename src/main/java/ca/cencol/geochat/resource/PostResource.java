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
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import ca.cencol.geochat.model.IncommingMessage;
import ca.cencol.geochat.service.RoomService;
import ca.cencol.geochat.service.ServiceFactory;

@Path("/post")
@Api(value = "/post", description = "Post message service")
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

  private final RoomService roomService = ServiceFactory.createRoomService();

  @GET
  @ApiOperation(httpMethod = "GET", value = "Post a message", notes = "MUST NOT be used by the client. For testing only")
  @Path("/{userId}/{locationId}/{message}")
  public Response post(
      @ApiParam(value = "Unique user identifier", required = true) @PathParam("userId") String userId,
      @ApiParam(value = "Current user location", required = true) @PathParam("locationId") String roomId,
      @ApiParam(value = "Message", required = true, allowableValues = "Non-empty string") @PathParam("message") String message) {

    roomService.postMessage(userId, roomId, message);

    return Response.ok().build();
  }

  @POST
  @ApiOperation(httpMethod = "POST", value = "Post a message", notes = "Posts message fetched from the request body")
  @ApiImplicitParam(name = "body", value = "Message being posted", required = true, dataType = "IncommingMessage", paramType = "body")
  @Path("/{userId}/{locationId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response postPost(
      @ApiParam(value = "Unique user identifier", required = true) @PathParam("userId") String userId,
      @ApiParam(value = "Current user location", required = true) @PathParam("locationId") String roomId,
      IncommingMessage message) {

    roomService.postMessage(userId, roomId, message.toString());

    return Response.ok().build();
  }

}
