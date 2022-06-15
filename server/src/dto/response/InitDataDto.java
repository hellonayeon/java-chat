package dto.response;

import domain.ChatRoom;
import domain.User;
import dto.type.MessageType;

import java.util.List;
import java.util.Map;

public class InitDataDto extends DTO {

    private Map<String, ChatRoom> chatMap;

    private List<User> users;

    private String curChatRoomId;

    public InitDataDto(Map<String, ChatRoom> chatMap, List<User> users, String curChatRoomId) {
        super(MessageType.LOGIN);

        this.chatMap = chatMap;
        this.users = users;
        this.curChatRoomId = curChatRoomId;
    }

    @Override
    public String toString() {
        String str = super.toString();

        if (chatMap.size() != 0) {
            for (String key : chatMap.keySet()) {
                ChatRoom chatRoom = chatMap.get(key);

                str += chatRoom.getId() + "," + chatRoom.getName() + "|";
            }
            str = str.substring(0, str.length()-1);

        }
        str += "+";


        for (User user : users) {
            str += user.getId() + "," + user.getName() + "|";
        }
        str = str.substring(0, str.length()-1);
        str += "+";
        str += curChatRoomId;

        return str;
    }
}
