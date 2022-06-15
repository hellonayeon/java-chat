package dto.request;

import dto.type.DtoType;
import dto.type.MessageType;

public class MessageRequest extends DTO {

    MessageType messageType;

    String chatRoomName;

    String userName;

    String message;


    public MessageRequest(MessageType messageType, String chatRoomName, String userName, String message) {
        super(DtoType.MESSAGE);

        this.messageType = messageType;
        this.chatRoomName = chatRoomName;
        this.userName = userName;
        this.message = message;
    }

    @Override
    public String toString() {
        return super.toString() + messageType + "," + chatRoomName + "," + userName + "," + message;
    }
}
