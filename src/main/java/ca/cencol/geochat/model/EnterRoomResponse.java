package ca.cencol.geochat.model;

import java.util.Collection;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Contains all room messages")
public class EnterRoomResponse {

  @ApiModelProperty(value = "roomId or locationId", required = true)
  private final String roomId;
  @ApiModelProperty(value = "All room messages", required = true)
  private final Collection<Message> messages;

  public EnterRoomResponse(String roomId, Collection<Message> messages) {
    super();
    this.roomId = roomId;
    this.messages = messages;
  }

  public String getRoomId() {
    return roomId;
  }

  public Collection<Message> getMessages() {
    return messages;
  }

}
