package ca.cencol.geochat.resource;

import static java.lang.String.format;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.cencol.geochat.mapper.NotFoundException;
import ca.cencol.geochat.model.PrivateProfileResponse;
import ca.cencol.geochat.model.ProfileNewValue;
import ca.cencol.geochat.model.PublicProfileResponse;
import ca.cencol.geochat.model.User;
import ca.cencol.geochat.service.ServiceFactory;
import ca.cencol.geochat.service.UsersService;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/profile")
@Api(value = "/profile", description = "Profile service")
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

  private final UsersService usersService = ServiceFactory.createUsersService();

  @GET
  @ApiOperation(httpMethod = "GET",
      value = "Get public profile",
      notes = "Get public profile info by user ID",
      response = PublicProfileResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 404, message = "User ID not found") })
  @Path("/public/{userId}")
  @Produces(MediaType.APPLICATION_JSON)
  public PublicProfileResponse getPublicProfile(
      @ApiParam(value = "Unique user identifier", required = true) @PathParam("userId") String userId) {

    User user = usersService.getUser(userId);
    // check if a user with a given ID exists
    if (user == null) {
      throw new NotFoundException(format("User ID %s not found", userId));
    }

    return new PublicProfileResponse(
        user.getUserId(),
        user.getUsername(),
        user.getImageId(),
        user.getAdditionalInfo());

  }

  @GET
  @ApiOperation(httpMethod = "GET",
      value = "Get private profile",
      notes = "Get private profile info by user ID",
      response = PrivateProfileResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 404, message = "User ID not found") })
  @Path("/private/{userId}")
  @Produces(MediaType.APPLICATION_JSON)
  public PrivateProfileResponse getPrivateProfile(
      @ApiParam(value = "Unique user identifier", required = true) @PathParam("userId") String userId) {

    User user = usersService.getUser(userId);
    // check if a user with a given ID exists
    if (user == null) {
      throw new NotFoundException(format("User ID %s not found", userId));
    }

    return new PrivateProfileResponse(
        user.getUserId(),
        user.getUsername(),
        user.getEmail(),
        user.getImageId(),
        user.getAdditionalInfo());

  }
  
  @POST
  @ApiOperation(httpMethod = "POST", value = "Update additional info")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Additional info has been updated"),
      @ApiResponse(code = 404, message = "User ID not found")})
  @Path("/addInfo/{userId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateAdditionalInfo(
      @ApiParam(value = "Unique user identifier", required = true) @PathParam("userId") String userId,
      @ApiParam(value = "Profile new value", required = true) ProfileNewValue newValue) {

    usersService.updateAdditionalInfo(userId, newValue.toString());

    return Response.ok().build();
  }
  
  @POST
  @ApiOperation(httpMethod = "POST", value = "Update image ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Image ID has been updated"),
      @ApiResponse(code = 404, message = "User ID not found")})
  @Path("/imageId/{userId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateImageId(
      @ApiParam(value = "Unique user identifier", required = true) @PathParam("userId") String userId,
      @ApiParam(value = "Profile new value", required = true) ProfileNewValue newValue) {

    usersService.updateImageId(userId, newValue.toString());

    return Response.ok().build();
  }
}
