package ca.cencol.geochat.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lombok.val;
import ca.cencol.geochat.model.RegistrationResponse;
import ca.cencol.geochat.model.RegistrationUser;
import ca.cencol.geochat.service.ServiceFactory;
import ca.cencol.geochat.service.UsersService;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/register")
@Api(value = "/register", description = "Register a user")
public class RegisterResource {
  
  private final UsersService usersService = ServiceFactory.createUsersService();
  
  @POST
  @ApiOperation(httpMethod = "POST", value = "Register a user", response = RegistrationResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "User has been registered"),
      @ApiResponse(code = 400, message = "Request does not contain all the information or such username/email are already used")})
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public RegistrationResponse register(@ApiParam(value = "Registration information", required = true) RegistrationUser user) {
    val id = usersService.registerUser(user);
    val response = new RegistrationResponse(id);
    
    return response;
  }

}
