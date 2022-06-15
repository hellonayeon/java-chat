package dto.response;

import dto.type.MessageType;

public class MessageResponse {

    MessageType messageType;

    String chatRoomName;

    String userName;

    String message;

    public MessageResponse(String message) {
        String[] value = message.split(",");
        this.messageType = MessageType.valueOf(value[0]);
        this.chatRoomName = value[1];
        this.userName = value[2];
        this.message = value[3];
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }
}
