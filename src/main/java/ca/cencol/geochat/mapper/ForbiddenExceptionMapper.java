package ca.cencol.geochat.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {
  
  private final static Status FORBIDDEN = Status.FORBIDDEN;

  @Override
  public Response toResponse(ForbiddenException exception) {
    return Response
        .status(FORBIDDEN)
        .type(MediaType.TEXT_PLAIN)
        .entity(exception.getMessage())
        .build();
  }

}
