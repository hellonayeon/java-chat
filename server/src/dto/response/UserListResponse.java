package dto.response;

import domain.User;
import dto.type.DtoType;

import java.util.List;

public class UserListResponse extends DTO {

    String chatRoomName;

    List<User> users;

    public UserListResponse(String chatRoomName, List<User> users) {
        super(DtoType.USER_LIST);

        this.chatRoomName = chatRoomName;
        this.users = users;
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += chatRoomName + "|";
        for (User user : users) {
            str += user.getId() + "," + user.getName() + "|";

        }

        return str.substring(0, str.length() - 1);
    }

    public List<User> getUsers() {
        return users;
    }
}
