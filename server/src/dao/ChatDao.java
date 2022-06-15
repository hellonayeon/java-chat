package dao;

import domain.ChatRoom;
import domain.User;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class ChatDao {

    private Vector<Socket> sockets = new Vector<>(); // 연결된 모든 사용자 소켓

    private Vector<User> users = new Vector<>(); // 접속 중인 모든 사용자 리스트

    private List<ChatRoom> chatRooms = new ArrayList<>();

    private ChatRoom lobby; // 로비 채팅방 정보 (로비를 계속 거쳐 다니니 로비 정보 저장)

    public final static String LOBBY_CHAT_NAME = "Lobby";


    public ChatDao() {
        // 로비 채팅방 기본으로 생성해두기
        lobby = new ChatRoom("Lobby");
    }

    public void addSocket(Socket socket) {
        sockets.add(socket);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addChatRoom(ChatRoom chatRoom) {
        chatRooms.add(chatRoom);
    }

    public void addChatRoomUser(String chatRoomName, User user) {
        Optional<ChatRoom> findChatRoom = chatRooms.stream()
                .filter(chatRoom -> chatRoom.getName().equals(chatRoomName))
                .findAny();

        if (findChatRoom.isEmpty()) {
            System.out.println("[" + chatRoomName  + "] 이름의 채팅방 없음");
        }
        else {
            findChatRoom.get().addUser(user);
        }
    }

    public void removeChatRoomUser(String chatRoomName, User user) {
        Optional<ChatRoom> findChatRoom = chatRooms.stream()
                .filter(chatRoom -> chatRoom.getName().equals(chatRoomName))
                .findAny();

        if (findChatRoom.isEmpty()) {
            System.out.println("[" + chatRoomName  + "] 이름의 채팅방 없음");
        }
        else {
            // TODO 사용자 존재 체크
            List<User> users = findChatRoom.get().getUsers();
            users.remove(user);
        }
    }

    public Optional<User> findUserById(String id) {
        return users.stream().filter(user -> user.getId().equals(id)).findAny();
    }

    public Optional<ChatRoom> findChatRoomByName(String name) {
        // TODO 예외처리 - 채팅방이 없는 경우 (Service 단에서 체크)
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

    public List<ChatRoom> getChatRooms() { return chatRooms; }
}
