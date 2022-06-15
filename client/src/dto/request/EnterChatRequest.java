package dto.request;

import dto.type.DtoType;

public class EnterChatRequest extends DTO {

    String chatRoomName;

    String userId;

    public EnterChatRequest(String chatRoomName, String userId) {
        super(DtoType.ENTER_CHAT);

        this.chatRoomName = chatRoomName;
        this.userId = userId;
    }

    public String getChatRoomId() {
        return chatRoomName;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return super.toString() + chatRoomName + "," + userId;
    }
}
