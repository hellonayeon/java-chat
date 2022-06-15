package dto.response;

import domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserListResponse {

    String chatRoomName;

    List<User> users;

    public UserListResponse(String message) {
        users = new ArrayList<>();

        String[] userValues = message.split("\\|");
        chatRoomName = userValues[0];

        for (int i = 1; i<userValues.length; i++) {
            String[] value = userValues[i].split(",");
            users.add(new User(value[0], value[1]));
        }

    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public List<User> getUsers() {
        return users;
    }
}
