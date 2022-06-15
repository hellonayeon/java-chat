package dto.response;

import domain.ChatRoom;
import domain.User;

import java.util.*;

public class InitResponse {

    private List<ChatRoom> chatRooms = new ArrayList<>();

    private List<User> users = new ArrayList<>();

    public InitResponse(String message) {
        System.out.println(message);
        String[] data = message.split("\\+");
        String[] chatRoomNames = data[0].split("\\|");

        for (String name : chatRoomNames) {
            if (name.isEmpty()) {
                break;
            }
            chatRooms.add(new ChatRoom(name));
        }

        String[] userValues = data[1].split("\\|");
        for (String userValue : userValues) {
            String[] elem = userValue.split(",");
            users.add(new User(elem[0], elem[1]));
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public List<ChatRoom> getChatRooms() {
        return chatRooms;
    }
}
