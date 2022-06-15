package dto.response;

import app.Application;
import domain.ChatRoom;
import domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitDataDto {

    private Map<String, ChatRoom> chatMap = new HashMap<>();

    private List<User> users = new ArrayList<>();

    private String chatRoomId;

    public InitDataDto(String message) {
        System.out.println(message);
        String[] data = message.split("\\+");
        String[] mapValues = data[0].split("\\|");

        for (String mapValue : mapValues) {
            if (mapValue.isEmpty()) {
                break;
            }
            String[] elem = mapValue.split(",");
            chatMap.put(elem[0], getChatRoom(elem[0], elem[1]));
        }

        String[] listValues = data[1].split("\\|");
        for (String listValue : listValues) {
            String[] elem = listValue.split(",");
            users.add(getUser(elem[0], elem[1]));
        }

        chatRoomId = data[2];
    }

    public ChatRoom getChatRoom(String id, String name) {
        return new ChatRoom(id, name);
    }

    public User getUser(String id, String name) {
        return new User(id, name);
    }

    public Map<String, ChatRoom> getChatMap() {
        return chatMap;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }
}
