package thread;

import app.Application;
import dao.ChatDao;
import domain.ChatRoom;
import domain.User;
import dto.request.*;
import dto.response.*;
import dto.type.DtoType;
import dto.type.MessageType;
import exception.ChatRoomExistException;
import exception.ChatRoomNotFoundException;
import exception.UserNotFoundException;
import service.ChatService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerThread extends Thread {

    Socket socket; // 담당자 (not 문지기)

    ChatService chatService;

    public ServerThread(Socket socket, ChatService chatService) {
        this.socket = socket;
        this.chatService = chatService;
    }

    @Override
    public void run() {
        super.run();

        try {
            while (true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str = reader.readLine();
                if (str == null) {
                    System.out.println("socket error (can't get socket input stream) - client socket closed");
                    try {

                        socket.close();
                        System.out.println("socket closed.");

                        Application.sockets.remove(socket);
                        return;

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                String[] token = str.split(":");
                DtoType type = DtoType.valueOf(token[0]);
                String message = token[1];

                processReceiveMessage(type, message);

                Thread.sleep(300);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void processReceiveMessage(DtoType type, String message)
            throws UserNotFoundException, ChatRoomNotFoundException, ChatRoomExistException, IOException {
        switch (type) {
            case LOGIN:
                // 사용자 추가
                LoginRequest loginReq = new LoginRequest(message);
                User user = new User(socket, loginReq);
                chatService.addUser(user);
                chatService.enterLobby(user); // 처음 채팅방에 들어가면 로비로

                // [to 채팅방에 있는 다른 사용자] 입장 메시지 전송
                MessageResponse lobbyEnterMessageRes = new MessageResponse(MessageType.ENTER, ChatDao.LOBBY_CHAT_NAME, user.getName(), user.getEnterString());
                sendMessage(lobbyEnterMessageRes);


                // [to 로그인 사용자] 초기 데이터 전송 (전체 사용자 리스트, 채팅방 리스트)
                List<ChatRoom> chatRooms = chatService.getChatRooms();
                List<User> users = chatService.getUsers();

                InitDataResponse initRes = new InitDataResponse(chatRooms, users);
                sendMessage(initRes);

                // [to 채팅방에 있는 다른 사용자] 사용자 리스트 전송
                UserListResponse userListRes = new UserListResponse(ChatDao.LOBBY_CHAT_NAME, chatService.getUsers());
                sendMessage(userListRes);
                break;

            case MESSAGE:
                // [to 채팅방에 있는 다른 사용자] 메시지 전송
                MessageResponse messageResponse = new MessageResponse(message);
                sendMessage(messageResponse);
                break;

            case CREATE_CHAT:
                CreateChatRoomRequest createChatRoomReq = new CreateChatRoomRequest(message);

                ChatRoom chatRoom = chatService.createChatRoom(createChatRoomReq.getName(), createChatRoomReq.getUserId());
                chatService.enterChatRoom(chatRoom.getName(), createChatRoomReq.getUserId());

                // 새로 생성된 방 정보 전송
                CreateChatRoomResponse createChatRoomRes = new CreateChatRoomResponse(chatRoom);
                sendMessage(createChatRoomRes);

                // [to 채팅방에 있는 모든 사용자 (나 자신 포함)] 사용자 리스트 전송
                UserListResponse chatRoomUserListRes = new UserListResponse(chatRoom.getName(), chatService.getChatRoomUsers(chatRoom.getName()));
                sendMessage(chatRoomUserListRes);
                break;

            case ENTER_CHAT:
                // 서버에 채팅방에 들어온 사용자 설정
                EnterChatRequest enterChatReq = new EnterChatRequest(message);
                String enterChatRoomName = enterChatReq.getChatRoomName();
                String userId = enterChatReq.getUserId();
                chatService.enterChatRoom(enterChatRoomName, userId);

                // [to 채팅방에 있는 다른 사용자] 입장 메시지 전송
                User enterUser = chatService.getUser(userId);
                MessageResponse enterChatRoomEnterMessageRes = new MessageResponse(MessageType.ENTER, enterChatRoomName, enterUser.getName(), enterUser.getEnterString());
                sendMessage(enterChatRoomEnterMessageRes);

                // [to 채팅방에 있는 모든 사용자 (나 자신 포함)] 사용자 리스트 전송
                UserListResponse enterChatRoomUserListRes = new UserListResponse(enterChatRoomName, chatService.getChatRoomUsers(enterChatRoomName));
                sendMessage(enterChatRoomUserListRes);

                break;
            case EXIT_CHAT:
                ExitChatRequest exitChatReq = new ExitChatRequest(message);
                ChatRoom exitChatRoom = chatService.getChatRoom(exitChatReq.getChatRoomName());
                String exitChatRoomName = exitChatRoom.getName();
                User exitUser = chatService.exitChatRoom(exitChatReq.getChatRoomName(), exitChatReq.getUserId());

                if (exitChatRoom.ieExistUser()) {
                    // [to 채팅방에 있는 다른 사용자] 퇴장 메시지 전송
                    MessageResponse chatRoomExitMessageRes = new MessageResponse(MessageType.EXIT, exitChatReq.getChatRoomName(), exitUser.getName(), exitUser.getExitString());
                    sendMessage(chatRoomExitMessageRes);

                    // [to 채팅방에 있는 모든 사용자 (나 자신 포함)] 사용자 리스트 전송
                    UserListResponse exitChatRoomUserListRes = new UserListResponse(exitChatRoomName, chatService.getChatRoomUsers(exitChatRoomName));
                    sendMessage(exitChatRoomUserListRes);
                }

                // 채팅방에 더 이상 사용자가 없는 경우
                // 채팅방 목록 갱신
                else {
                    ChatRoomListResponse chatRoomListRes = new ChatRoomListResponse(chatService.getChatRooms());
                    sendMessage(chatRoomListRes);
                }

                break;
        }
    }

    private void sendMessage(DTO dto) {
        DtoType type = dto.getType();

        try {
            PrintWriter sender = null;
            switch (type) {
                case LOGIN:
                    InitDataResponse initDataResponse = (InitDataResponse) dto;

                    // 로그인 한 자신에게만 전송
                    sender = new PrintWriter(socket.getOutputStream());
                    sender.println(initDataResponse);
                    sender.flush();
                    break;

                    // 채팅방에 표시되는 메시지 전송 (입장 메시지, 대화 메시지)
                case MESSAGE:
                    MessageResponse messageReq = (MessageResponse) dto;

                    ChatRoom chatRoom = chatService.getChatRoom(messageReq.getChatRoomName());
                    List<User> chatUsers = chatRoom.getUsers();

                    // 나를 제외한 사용자에게 모두 출력
                    for (User user : chatUsers) {
                        Socket s = user.getSocket();
                        if (s != socket) {
                            sender = new PrintWriter(s.getOutputStream());
                            sender.println(messageReq);
                            sender.flush();
                        }
                    }
                    break;

                case USER_LIST:
                    // 사용자 리스트 추가
                    // dto 에 담긴 사용자 리스트에 따라 추가
                    UserListResponse userListRes = (UserListResponse) dto;

                    for (User user : userListRes.getUsers()) {
                        Socket s = user.getSocket();
                        sender = new PrintWriter(s.getOutputStream());
                        sender.println(userListRes);
                        sender.flush();
                    }
                    break;

                case CREATE_CHAT:
                    CreateChatRoomResponse createChatRoomResponse = (CreateChatRoomResponse) dto;

                    for (Socket s : Application.sockets) {
                        sender = new PrintWriter(s.getOutputStream());
                        sender.println(createChatRoomResponse);
                        sender.flush();
                    }
                    break;

                case CHAT_ROOM_LIST:
                    ChatRoomListResponse chatRoomListResponse = (ChatRoomListResponse) dto;

                    for (Socket s : Application.sockets) {
                        sender = new PrintWriter(s.getOutputStream());
                        sender.println(chatRoomListResponse);
                        sender.flush();
                    }
                    break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
