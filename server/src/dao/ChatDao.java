package dao;

import domain.ChatRoom;
import domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChatDao {

    private List<User> users = new ArrayList<>(); // 접속 중인 모든 사용자 리스트

    private List<ChatRoom> chatRooms = new ArrayList<>();

    private ChatRoom lobby; // 로비 채팅방 정보 (로비를 계속 거쳐 다니니 로비 정보 저장)

    public final static String LOBBY_CHAT_NAME = "Lobby";


    public ChatDao() {
        // 로비 채팅방 기본으로 생성해두기
        lobby = new ChatRoom("Lobby");
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addChatRoom(ChatRoom chatRoom) {
        chatRooms.add(chatRoom);
    }

    public Optional<User> findUserById(String id) {
        return users.stream().filter(user -> user.getId().equals(id)).findAny();
    }

    public Optional<ChatRoom> findChatRoomByName(String name) {
        return chatRooms.stream()
                .filter(chatRoom -> chatRoom.getName().equals(name))
                .findAny();
    }

    public ChatRoom getLobby() {
        return lobby;
    }

    public List<User> getUsers() {
        return users;
    }

    public Optional<User> getUser(String userId) {
        return users.stream().filter(user -> user.getId().equals(userId)).findAny();
    }

    public List<ChatRoom> getChatRooms() { return chatRooms; }
}
