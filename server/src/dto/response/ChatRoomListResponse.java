package dto.response;

import domain.ChatRoom;
import dto.type.DtoType;

import java.util.List;

public class ChatRoomListResponse extends DTO {

    List<ChatRoom> chatRooms;

    public ChatRoomListResponse(List<ChatRoom> chatRooms) {
        super(DtoType.CHAT_ROOM_LIST);

        this.chatRooms = chatRooms;
    }

    @Override
    public String toString() {
        String str = super.toString();

        for (ChatRoom chatRoom : chatRooms) {
            str += chatRoom.getName() + ",";
        }
        return (chatRooms.size() > 0) ? str.substring(0, str.length() - 1) : str + "empty";
    }
}
