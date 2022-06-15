package dao;

import domain.ChatRoom;
import domain.User;

import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.Vector;

public class ChatDao {

    private Vector<Socket> sockets = new Vector<>(); // 연결된 모든 사용자 소켓

    private Vector<User> users = new Vector<>(); // 접속 중인 모든 사용자 리스트

    private ConcurrentMap<String, ChatRoom> chatMap = new ConcurrentHashMap<>(); // 채팅방 목록 [key] 채팅방 ID (UUID) [value] 채팅방

    private ChatRoom lobby; // 로비 채팅방 정보 (로비를 계속 거쳐 다니니 로비 정보 저장)


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
        chatMap.put(chatRoom.getId(), chatRoom);
    }

    public void addChatRoomUser(String chatRoomId, User user) {
        if (chatRoomId.equals(lobby.getId())) {
            lobby.addUser(user);
        }
        else {
            chatMap.get(chatRoomId).addUser(user);
        }
    }

    public void removeChatRoomUser(String chatRoomId, User user) {
        System.out.println(chatRoomId + "---" + user);
        chatMap.get(chatRoomId).removeUser(user);
    }

    public Optional<User> findUserById(String id) {
        return users.stream().filter(user -> user.getId().equals(id)).findAny();
    }

    public ChatRoom findChatRoomById(String id) {
        if (id.equals(lobby.getId())) {
            return lobby;
        }
        // TODO 예외처리 - 채팅방이 없는 경우
        return chatMap.get(id);
    }

    public ChatRoom getLobby() {
        return lobby;
    }

    public String getLobbyId() {
        return lobby.getId();
    }

    public List<User> getUsers() {
        return users;
    }

    public Map<String, ChatRoom> getChatMap() { return chatMap; }
}
