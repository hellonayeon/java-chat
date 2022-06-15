package dto.request;

import dto.type.MessageType;

public class CreateChatRoomDto extends DTO {

    String name;

    String userId;

    public CreateChatRoomDto(String name, String userId) {
        super(MessageType.CREATE_CHAT);

        this.name = name;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return super.toString() + name + "," + userId;
    }
}
