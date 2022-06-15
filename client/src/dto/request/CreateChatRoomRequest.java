package dto.request;

import dto.type.DtoType;

public class CreateChatRoomRequest extends DTO {

    String chatRoomName;

    String userId;

    public CreateChatRoomRequest(String chatRoomName, String userId) {
        super(DtoType.CREATE_CHAT);

        this.chatRoomName = chatRoomName;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return super.toString() + chatRoomName + "," + userId;
    }
}
