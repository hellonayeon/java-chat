package dto.response;

import domain.ChatRoom;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomListResponse {

    List<ChatRoom> chatRooms = new ArrayList<>();

    public ChatRoomListResponse(String message) {
        if (!message.equals("empty")) {
            String[] names = message.split(",");
            for(String name : names) {
                chatRooms.add(new ChatRoom(name));
            }
        }
    }

    public List<ChatRoom> getChatRooms() {
        return chatRooms;
    }
}
