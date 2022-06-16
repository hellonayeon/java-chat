package service;

import app.Application;
import dao.ChatDao;
import domain.ChatRoom;
import domain.User;
import exception.ChatRoomExistException;
import exception.ChatRoomNotFoundException;
import exception.UserNotFoundException;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Optional;

public class ChatService {

    ChatDao chatDao;

    public ChatService(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    // 사용자 정보 추가
    public void addUser(User user) {
        chatDao.addUser(user);
    }

    public void enterLobby(User user) {
        chatDao.getLobby().addUser(user);
    }

    // 채팅방 입장
    public void enterChatRoom(String chatRoomName, String userId) throws UserNotFoundException, ChatRoomNotFoundException {
        Optional<User> findUser = chatDao.findUserById(userId);
        if (findUser.isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        List<ChatRoom> chatRooms = chatDao.getChatRooms();
        Optional<ChatRoom> findChatRoom = chatRooms.stream()
                .filter(chatRoom -> chatRoom.getName().equals(chatRoomName))
                .findAny();

        if (findChatRoom.isEmpty()) {
            throw new ChatRoomNotFoundException(chatRoomName);
        }

        findChatRoom.get().addUser(findUser.get());
    }

    // 채팅방 생성
    public ChatRoom createChatRoom(String chatRoomName, String userId) throws ChatRoomExistException {
        Optional<ChatRoom> findChatRoom = chatDao.getChatRooms().stream()
                .filter(chatRoom -> chatRoom.getName().equals(chatRoomName))
                .findAny();

        if (findChatRoom.isEmpty()) {
            ChatRoom chatRoom = new ChatRoom(chatRoomName);
            chatDao.addChatRoom(chatRoom);
            return chatRoom;
        }
        else {
            throw new ChatRoomExistException(chatRoomName);
        }
    }

    // 채팅방 나가기
    public User exitChatRoom(String chatRoomName, String userId) throws UserNotFoundException, ChatRoomNotFoundException {
        Optional<ChatRoom> chatRoom = chatDao.findChatRoomByName(chatRoomName);
        if (chatRoom.isEmpty()) {
            throw new ChatRoomNotFoundException(chatRoomName);
        }

        Optional<User> findUser = chatRoom.get().getUsers().stream()
                .filter(user -> user.getId().equals(userId))
                .findAny();

        if (findUser.isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        List<User> users = chatRoom.get().getUsers();
        users.remove(findUser.get());

        if (!chatRoom.get().ieExistUser()) {
            chatDao.getChatRooms().remove(chatRoom.get());
        }

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

    public void disconnect(String userId) throws UserNotFoundException, IOException {
        Optional<User> findUser = chatDao.getUser(userId);
        if (findUser.isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        // 사용자가 입장해있는 채팅방에서 사용자 삭제
        List<ChatRoom> chatRooms = chatDao.getChatRooms();
        chatRooms.forEach(chatRoom -> chatRoom.removeUser(findUser.get())); // TODO 스트림 람다식 수정

        // 전체 사용자 리스트에서 제거
        List<User> users = chatDao.getUsers();
        users.remove(findUser.get());

        // 소켓 닫기 및 소켓 리스트에서 제거
        List<Socket> sockets = Application.sockets;
        Socket clientSocket = findUser.get().getSocket();
        clientSocket.close();
        sockets.remove(findUser.get().getSocket());
    }
}
