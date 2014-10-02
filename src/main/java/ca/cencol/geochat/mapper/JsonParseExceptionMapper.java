package ca.cencol.geochat.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.JsonParseException;


/**
 * Thrown when incoming message could not be parsed
 */
@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {

  private final static Status BAD_REQUEST = Status.BAD_REQUEST;

  @Override
  public Response toResponse(JsonParseException exception) {
    return Response
        .status(BAD_REQUEST)
        .type(MediaType.TEXT_PLAIN)
        .entity(exception.getMessage())
        .build();
  }

}
