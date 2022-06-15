package dto.request;

import dto.type.MessageType;

public class EnterChatDto extends DTO {

    String chatRoomId;

    String userId;

    public EnterChatDto(String chatRoomId, String userId) {
        super(MessageType.ENTER_CHAT);

        this.chatRoomId = chatRoomId;
        this.userId = userId;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return super.toString() + chatRoomId + "," + userId;
    }
}
