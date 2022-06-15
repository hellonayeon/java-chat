package dto.response;

import dto.type.DtoType;
import dto.type.MessageType;

public class MessageResponse extends DTO {

    MessageType messageType;

    String chatRoomName;

    String userName;

    String message;

    public MessageResponse(String message) {
        super(DtoType.MESSAGE);

        String[] value = message.split(",");
        messageType = MessageType.valueOf(value[0]);
        chatRoomName = value[1];
        userName = value[2];
        this.message = value[3];
    }

    public MessageResponse(MessageType messageType, String chatRoomName, String userName, String message) {
        super(DtoType.MESSAGE);

        this.messageType = messageType;
        this.chatRoomName = chatRoomName;
        this.userName = userName;
        this.message = message;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    @Override
    public String toString() {
        return super.toString() + messageType + "," + chatRoomName + "," + userName + "," + message;
    }
}
