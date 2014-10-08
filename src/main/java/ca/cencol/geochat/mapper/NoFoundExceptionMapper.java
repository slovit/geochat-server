package ca.cencol.geochat.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
  
  private final static Status NOT_FOUND = Status.NOT_FOUND;

  @Override
  public Response toResponse(NotFoundException exception) {
    return Response
        .status(NOT_FOUND)
        .type(MediaType.TEXT_PLAIN)
        .entity(exception.getMessage())
        .build();
  }

}
