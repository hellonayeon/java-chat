package domain;

import java.util.Date;
import java.util.List;

public class ChatRoom {

    List<User> users;

    String id; // 채팅방 아이디 (식별자)

    String name; // 채팅방 이름

    Date createdAt;

    public ChatRoom(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
