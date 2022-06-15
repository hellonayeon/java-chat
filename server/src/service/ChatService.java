package service;

import dao.ChatDao;
import domain.ChatRoom;
import domain.User;
import dto.request.CreateChatRoomDto;
import dto.request.EnterChatDto;
import dto.request.ExitChatDto;

import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
    public void login(User user) {
        chatDao.addUser(user);
    }

    public void enterLobby(User user) {
        chatDao.addChatRoomUser(chatDao.getLobbyId(), user);
    }

    // 채팅방 생성
    public ChatRoom createChatRoom(CreateChatRoomDto dto) {
        ChatRoom chatRoom = new ChatRoom(dto.getName());
        chatDao.addChatRoom(chatRoom);

        return chatRoom;
    }

    // 채팅방 입장
    public void enterChatRoom(EnterChatDto dto) {
        Optional<User> findUser = chatDao.findUserById(dto.getUserId());
        // TODO 예외처리
        if (findUser.isEmpty()) {
            System.out.println(dto.getUserId() + "아이디의 사용자 없음");
            return;
        }
        // 로비 -> 채팅방
        chatDao.removeChatRoomUser(chatDao.getLobbyId(), findUser.get());
        chatDao.addChatRoomUser(dto.getChatRoomId(), findUser.get());
    }

    // 채팅방 나가기
    public User exitChatRoom(String userId, String chatRoomId) {
        Optional<User> findUser = chatDao.findUserById(userId);
        // TODO 예외처리
        if (findUser.isEmpty()) {
            System.out.println(userId + "아이디의 사용자 없음");
            return null;
        }
        chatDao.removeChatRoomUser(chatRoomId, findUser.get());
        return findUser.get();
    }

    public ChatRoom getChatRoom(String chatRoomId) {
        return chatDao.findChatRoomById(chatRoomId);
    }

    public ChatRoom getLobby() {
        return chatDao.getLobby();
    }

    public List<User> getUsers() {
        return chatDao.getUsers();
    }

    public Map<String, ChatRoom> getChatMap() { return chatDao.getChatMap(); }

    public User getUser(String userId) {
        chatDao.getUsers().stream().forEach(user -> System.out.println(user.getId()));
        Optional<User> findUser = chatDao.findUserById(userId);
        if (findUser.isEmpty()) {
            System.out.println(userId + "아이디의 사용자 없음");
            return null;
        }
        return findUser.get();
    }
}
