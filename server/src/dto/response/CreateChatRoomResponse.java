package dto.response;

import domain.ChatRoom;
import dto.type.DtoType;

public class CreateChatRoomResponse extends DTO {

    private String chatRoomName;

    public CreateChatRoomResponse(ChatRoom chatRoom) {
        super(DtoType.CREATE_CHAT);

        this.chatRoomName = chatRoom.getName();
    }

    @Override
    public String toString() {
        return super.toString() + chatRoomName ;
    }
}
