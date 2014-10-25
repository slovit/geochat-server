package ca.cencol.geochat.resource;

import static java.lang.String.format;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.cencol.geochat.mapper.NotFoundException;
import ca.cencol.geochat.model.PublicProfileResponse;
import ca.cencol.geochat.model.User;
import ca.cencol.geochat.service.ServiceFactory;
import ca.cencol.geochat.service.UsersService;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/getProfile")
@Api(value = "/getProfile", description = "Get profile")
@Produces(MediaType.APPLICATION_JSON)
public class GetProfileResource {

  private final UsersService usersService = ServiceFactory.createUsersService();

  @GET
  @ApiOperation(httpMethod = "GET", value = "Get profile", notes = "Get profile info by user ID",
      response = PublicProfileResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 404, message = "User ID not found") })
  @Path("/{userId}")
  @Produces(MediaType.APPLICATION_JSON)
  public PublicProfileResponse getProfile(
      @ApiParam(value = "Unique user identifier", required = true) @PathParam("userId") String userId) {

    User user = usersService.getUser(userId);
    // check if a user with a given ID exists
    if (user == null) {
      throw new NotFoundException(format("User ID %s not found", userId));
    }

    return new PublicProfileResponse(user.getUserId(), user.getUsername());

  }
}
