package network;

import app.Application;
import domain.ChatRoom;
import dto.response.*;
import dto.type.MessageType;
import view.frame.ClientFrame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageReceiver extends Thread {

    Socket socket;

    public MessageReceiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();

        try {
            while (true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str = reader.readLine();
                if (str == null) {
                    System.out.println("접속 끊김");
                }
                System.out.println(str);

                String[] token = str.split(":");
                MessageType type = MessageType.valueOf(token[0]);
                String message = token[1];

                processMessage(type, message);

                Thread.sleep(300);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void processMessage(MessageType type, String message) {
        System.out.println(message);

        switch (type) {
            case LOGIN:
                InitDataDto initDataDto = new InitDataDto(message);
                Application.chatMap = initDataDto.getChatMap();
                Application.users = initDataDto.getUsers();
                Application.curChatRoomId = initDataDto.getChatRoomId();

                ClientFrame.chatRoomUserListPanel.addChatUserLabel(Application.users); // 전체 리스트
                ClientFrame.chatRoomListPanel.addChatRoomLabel();
                break;
            case SEND_MESSAGE:
                ReceiveMessageDto receiveMessageDto = new ReceiveMessageDto(message);
                ClientFrame.chatPanel.addMessage(receiveMessageDto.getMessage());
                break;
            case CREATE_CHAT:
                CreateChatRoomResponse createChatRoomResponse = new CreateChatRoomResponse(message);
                String chatRoomId = createChatRoomResponse.getId();
                String chatName = createChatRoomResponse.getName();

                ChatRoom newChatRoom = new ChatRoom(chatRoomId, chatName);
                Application.chatMap.put(chatRoomId, newChatRoom);

                ClientFrame.chatRoomListPanel.addChatRoomLabel();
                break;
            case USER_LIST:
                UserListDto dto = new UserListDto(message);
                ClientFrame.chatRoomUserListPanel.addChatUserLabel(dto.getUsers());
                break;
            case CHAT_LIST:
//                ChatListResponse chatListResponse = new ChatListResponse(message);
//                ClientFrame.chatRoomListPanel.add
//                break;
        }
    }
}
