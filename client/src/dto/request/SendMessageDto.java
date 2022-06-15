package dto.request;

import dto.type.MessageType;

public class SendMessageDto extends DTO {

    private String chatRoomId;

    private String userId;

    private String message;


    public SendMessageDto(String chatRoomId, String userId, String message) {
        super(MessageType.SEND_MESSAGE);

        this.chatRoomId = chatRoomId;
        this.userId = userId;
        this.message = message;
    }

    @Override
    public String toString() {
        return super.toString() + chatRoomId + "," + userId + "," + message;
    }
}
