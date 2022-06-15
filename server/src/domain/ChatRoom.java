package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatRoom {

    String name; // 채팅방 이름

    List<User> users;

    public ChatRoom(String name) {
        this.name = name;
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) { users.remove(user); }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }
}
