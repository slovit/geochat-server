package ca.cencol.geochat.model;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Contains all room messages")
public class EnterRoomResponse {

  @ApiModelProperty(value = "roomId or locationId", required = true)
  private final String roomId;
  @ApiModelProperty(value = "All room messages", required = true)
  private final List<Message> messages;

  public EnterRoomResponse(String roomId, List<Message> messages) {
    super();
    this.roomId = roomId;
    this.messages = messages;
  }

  public String getRoomId() {
    return roomId;
  }

  public List<Message> getMessages() {
    return messages;
  }

}
