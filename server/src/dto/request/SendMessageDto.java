package dto.request;

import dto.response.DTO;
import dto.type.MessageType;

public class SendMessageDto extends DTO {

    String chatRoomId;

    String userId;

    String message;

    public SendMessageDto(String message) {
        super(MessageType.SEND_MESSAGE);

        String[] value = message.split(",");
        chatRoomId = value[0];
        userId = value[1];
        this.message = value[2];
    }

    public SendMessageDto(String chatRoomId, String userId, String message) {
        super(MessageType.SEND_MESSAGE);

        this.chatRoomId = chatRoomId;
        this.userId = userId;
        this.message = message;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return super.toString() + chatRoomId + "," + userId + "," + message;
    }
}
