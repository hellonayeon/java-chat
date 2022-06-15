package dto.response;

import domain.User;
import dto.type.MessageType;

import java.util.List;

public class ChatRoomUserListDto extends DTO {

    String chatRoomId;

    List<User> users;

    public ChatRoomUserListDto(String chatRoomId, List<User> users) {
        super(MessageType.USER_LIST);

        this.chatRoomId = chatRoomId;
        this.users = users;
    }

    @Override
    public String toString() {
        String str = super.toString() + ":";
        for (User user : users) {
            str += user.getId() + "," + user.getName() + "," + user.getCreatedAt() + "|";
        }

        return str;
    }
}
