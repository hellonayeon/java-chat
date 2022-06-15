package domain;

import java.util.Date;
import java.util.List;

public class ChatRoom {

    String name; // 채팅방 이름

    public ChatRoom(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
