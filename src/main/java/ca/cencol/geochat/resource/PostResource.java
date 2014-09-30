package ca.cencol.geochat.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.cencol.geochat.model.IncommingMessage;
import ca.cencol.geochat.service.RoomService;
import ca.cencol.geochat.service.ServiceFactory;


@Path("/post")
public class PostResource {
  
  private final RoomService roomService = ServiceFactory.createRoomService();
  
  @GET
  @Path("{userId}/{locationId}/{message}")
  public Response post(
      @PathParam("userId") String userId,
      @PathParam("locationId") String roomId,
      @PathParam("message") String message) {
    
    roomService.postMessage(userId, roomId, message);
    
    return Response.ok().build();
  }
  
  @POST
  @Path("{userId}/{locationId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response postPost(
      @PathParam("userId") String userId,
      @PathParam("locationId") String roomId,
      IncommingMessage message) {
    
    
    
    roomService.postMessage(userId, roomId, message.toString());
    
    return Response.ok().build();
  }

}
