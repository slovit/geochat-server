package ca.cencol.geochat.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import ca.cencol.geochat.model.LoginRequest;
import ca.cencol.geochat.model.RegistrationResponse;
import ca.cencol.geochat.service.ServiceFactory;
import ca.cencol.geochat.service.UsersService;

@Path("/login")
@Api(value = "/login", description = "Login a user")
public class LoginResource {
  
  private final UsersService usersService = ServiceFactory.createUsersService();
  
  @POST
  @ApiOperation(httpMethod = "POST", value = "Login a user", response = RegistrationResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "User has been logined"),
      @ApiResponse(code = 400, message = "Request does not contain all the information"),
      @ApiResponse(code = 403, message = "No user found with such email and password")})
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public RegistrationResponse login(@ApiParam(value = "Login information", required = true) LoginRequest user) {
    
    return new RegistrationResponse(usersService.loginUser(user));
  }

}
