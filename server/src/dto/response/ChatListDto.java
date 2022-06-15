package dto.response;

import domain.ChatRoom;
import dto.type.DtoType;

import java.util.Map;

public class ChatListDto extends DTO {

    Map<String, ChatRoom> chatMap;

    public ChatListDto(Map<String, ChatRoom> chatMap) {
        super(DtoType.CHAT_LIST);

        this.chatMap = chatMap;
    }

    @Override
    public String toString() {
        String str = super.toString();

        for (String id : chatMap.keySet()) {
            ChatRoom chatRoom = chatMap.get(id);
            str += id + "," + chatRoom.getName() + "|";
        }
        return str.substring(0, str.length()-1);
    }
}
