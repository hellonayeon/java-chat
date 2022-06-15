package dto.response;

import domain.ChatRoom;

import java.util.HashMap;
import java.util.Map;

public class ChatListResponse {

    Map<String, ChatRoom> chatMap = new HashMap<>();

    public ChatListResponse(String message) {
        String[] values = message.split("\\|");
        for(String value : values) {
            String[] elem = value.split(",");
            chatMap.put(elem[0], new ChatRoom(elem[0], elem[1]));
        }
    }

    public Map<String, ChatRoom> getChatMap() {
        return chatMap;
    }
}
