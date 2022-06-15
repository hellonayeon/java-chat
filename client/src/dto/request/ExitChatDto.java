package dto.request;

import dto.type.MessageType;

public class ExitChatDto extends DTO {

    String chatRoomId;

    String userId;

    public ExitChatDto(String chatRoomId, String userId) {
        super(MessageType.EXIT_CHAT);

        this.chatRoomId = chatRoomId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return super.toString() + ":" + chatRoomId + "," + userId;
    }
}
