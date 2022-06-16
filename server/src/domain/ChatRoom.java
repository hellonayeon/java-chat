package domain;

import java.util.ArrayList;
import java.util.List;

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

    public void removeUser(User user) {
        try {
            users.remove(user);
        }
        catch (Exception e) {
            System.out.println("user = [" + user + "] is not exist in chat room = [" + name + "]" );
            System.out.println(e.getMessage());
        }

    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean ieExistUser() {
        return users.size() != 0;
    }
}
