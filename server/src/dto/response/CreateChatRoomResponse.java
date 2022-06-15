package dto.response;

import domain.ChatRoom;
import dto.type.MessageType;

public class CreateChatRoomResponse extends DTO {

    private String id;

    private String name;

    public CreateChatRoomResponse(ChatRoom chatRoom) {
        super(MessageType.CREATE_CHAT);

        this.id = chatRoom.getId();
        this.name = chatRoom.getName();
    }

    @Override
    public String toString() {
        return super.toString() + id + "," + name ;
    }
}
