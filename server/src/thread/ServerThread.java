package thread;

import app.Application;
import domain.ChatRoom;
import dto.response.*;
import dto.request.*;
import dto.type.MessageType;
import domain.User;
import service.ChatService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map;

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

                System.out.println(str);

                String[] token = str.split(":");
                MessageType type = MessageType.valueOf(token[0]);
                String message = token[1];

                processReceiveMessage(type, message);

                Thread.sleep(300);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processReceiveMessage(MessageType type, String message) {
        switch (type) {
            case LOGIN:
                // 사용자 추가
                LoginUserDto loginUserDto = new LoginUserDto(message);
                User user = new User(socket, loginUserDto);
                chatService.login(user);
                chatService.enterLobby(user);

                // 입장 메시지 전송
                ChatRoom lobby = chatService.getLobby();
                SendMessageDto enterMessageDto = new SendMessageDto(lobby.getId(), user.getId(), user.getEnterString());
                sendMessage(enterMessageDto);


                // 초기 데이터 전송 (전체 사용자 리스트, 채팅방 리스트)
                Map<String, ChatRoom> chatMap = chatService.getChatMap();
                List<User> users = chatService.getUsers();
                String lobbyRoomId = chatService.getLobby().getId();

                InitDataDto initDataDto = new InitDataDto(chatMap, users, lobbyRoomId);
                sendMessage(initDataDto);


                // 사용자 리스트 전송
                UserListDto userListDto = new UserListDto(chatService.getUsers());
                sendMessage(userListDto);
                break;
            case CREATE_CHAT:
                CreateChatRoomDto createChatRoomDto = new CreateChatRoomDto(message);

                ChatRoom chatRoom = chatService.createChatRoom(createChatRoomDto);
                User chatUser = chatService.getUser(createChatRoomDto.getUserId());
                chatRoom.addUser(chatUser); // socket 관리

                // 채팅방 리스트 전송
//                ChatListDto chatListDto = new ChatListDto(chatService.getChatMap());
//                sendMessage(chatListDto);

                CreateChatRoomResponse createChatRoomResponse = new CreateChatRoomResponse(chatRoom);
                sendMessage(createChatRoomResponse);
                break;
            case ENTER_CHAT:
                // TODO 현재 채팅방 설정
                chatService.enterChatRoom(new EnterChatDto(message));
                break;
            case EXIT_CHAT:
                ExitChatDto exitChatDto = new ExitChatDto(message);
                User exitUser = chatService.exitChatRoom(exitChatDto.getChatRoomId(), exitChatDto.getUserId());
                chatService.enterLobby(exitUser);
                break;
            case SEND_MESSAGE:
                // 채팅방 사용자들에게 메시지 전송
                SendMessageDto sendMessageDto = new SendMessageDto(message);
                sendMessage(sendMessageDto);
                break;
        }
    }

    private void sendMessage(DTO dto) {
        MessageType type = dto.getType();

        try {
            PrintWriter sender = null;
            switch (type) {
                case LOGIN:
                    InitDataDto initDataDto = (InitDataDto) dto;

                    // 로그인 한 자신에게만 전송
                    sender = new PrintWriter(socket.getOutputStream());
                    sender.println(initDataDto);
                    sender.flush();
                    break;
                case SEND_MESSAGE:
                    SendMessageDto sendMessageDto = (SendMessageDto) dto;

                    String chatRoomId = sendMessageDto.getChatRoomId();
                    if (chatRoomId.equals("null")) {
                        chatRoomId = chatService.getLobby().getId();
                    }
                    ChatRoom chatRoom = chatService.getChatRoom(chatRoomId);
                    List<User> chatUsers = chatRoom.getUsers();

                    // 나를 제외한 사용자에게 모두 출력
                    for (User user : chatUsers) {
                        Socket s = user.getSocket();
                        if (s != socket) {
                            sender = new PrintWriter(s.getOutputStream());
                            sender.println(sendMessageDto);
                            sender.flush();
                        }
                    }
                    break;
                case USER_LIST:
                    UserListDto userListDto = (UserListDto) dto;

                    for (Socket s : Application.sockets) {
                        sender = new PrintWriter(s.getOutputStream());
                        sender.println(userListDto);
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
                case CHAT_ROOM_USER_LIST:

                    break;
                case CHAT_LIST:
                    break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
