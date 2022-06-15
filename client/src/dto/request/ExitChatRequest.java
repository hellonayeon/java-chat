package dto.request;

import dto.type.DtoType;

public class ExitChatRequest extends DTO {

    String chatRoomName;

    String userId;

    public ExitChatRequest(String chatRoomName, String userId) {
        super(DtoType.EXIT_CHAT);

        this.chatRoomName = chatRoomName;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return super.toString() + chatRoomName + "," + userId;
    }
}
