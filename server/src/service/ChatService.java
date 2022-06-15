package service;

import dao.ChatDao;
import domain.ChatRoom;
import domain.User;

import java.net.Socket;
import java.util.List;
import java.util.Optional;

public class ChatService {

    ChatDao chatDao;

    public ChatService(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    public void connect(Socket socket) {
        chatDao.addSocket(socket);
    }

    // 사용자 정보 추가
    public void addUser(User user) {
        chatDao.addUser(user);
    }

    public void enterLobby(User user) {
        chatDao.getLobby().addUser(user);
    }

    // 채팅방 입장
    public void enterChatRoom(String chatRoomName, String userId) {
        Optional<User> findUser = chatDao.findUserById(userId);
        if (findUser.isEmpty()) {
            System.out.println("[" + userId + "]" + " 아이디의 사용자 없음");
            return;
        }
        chatDao.addChatRoomUser(chatRoomName, findUser.get());
    }

    // 채팅방 생성
    public ChatRoom createChatRoom(String chatRoomName, String userId) {
        Optional<ChatRoom> findChatRoom = chatDao.getChatRooms().stream()
                .filter(chatRoom -> chatRoom.getName().equals(chatRoomName))
                .findAny();

        if (findChatRoom.isEmpty()) {
            ChatRoom chatRoom = new ChatRoom(chatRoomName);
            chatDao.addChatRoom(chatRoom);
            return chatRoom;
        }
        else {
            // TODO 예외
            System.out.println("[" + chatRoomName + "] 이름의 채팅방 이미 존재" );
            return null;
        }
    }

    // 채팅방 나가기
    public User exitChatRoom(String chatRoomName, String userId) {
        Optional<User> findUser = chatDao.findUserById(userId);
        // TODO 예외처리
        if (findUser.isEmpty()) {
            System.out.println(userId + "아이디의 사용자 없음");
            return null;
        }
        chatDao.removeChatRoomUser(chatRoomName, findUser.get());
        return findUser.get();
    }

    public List<User> getUsers() {
        return chatDao.getUsers();
    }

    public List<ChatRoom> getChatRooms() { return chatDao.getChatRooms(); }

    public User getUser(String userId) {
        Optional<User> findUser = chatDao.findUserById(userId);
        if (findUser.isEmpty()) {
            System.out.println("[" + userId + "] 아이디의 사용자 없음");
            return null;
        }
        return findUser.get();
    }

    public ChatRoom getChatRoom(String chatRoomName) {
        if (chatRoomName.equals(ChatDao.LOBBY_CHAT_NAME)) {
            return chatDao.getLobby();
        }

        Optional<ChatRoom> findChatRoom = chatDao.findChatRoomByName(chatRoomName);
        if (findChatRoom.isEmpty()) {
            System.out.println("[" + chatRoomName + "] 이름의 채팅방 없음");
            return null;
        }
        return findChatRoom.get();
    }

    public List<User> getChatRoomUsers(String chatRoomName) {
        Optional<ChatRoom> findChatRoom = chatDao.findChatRoomByName(chatRoomName);
        if (findChatRoom.isEmpty()) {
            System.out.println("[" + chatRoomName + "] 이름의 채팅방 없음");
            return null;
        }
        return findChatRoom.get().getUsers();
    }
}
