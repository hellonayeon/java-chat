package network;

import app.Application;
import domain.ChatRoom;
import dto.response.*;
import dto.type.DtoType;
import view.frame.LobbyFrame;

import java.io.BufferedReader;
import java.io.IOException;
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
                    try {
                        socket.close();
                        System.out.println(Application.me.getName() + "'s socket is closed.");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println("disconnect");
                    System.exit(1);
                }
                System.out.println(str);
                String[] token = str.split(":");
                DtoType type = DtoType.valueOf(token[0]);
                String message = token[1];

                processReceivedMessage(type, message);

                Thread.sleep(300);
            }
        }
        catch (Exception e) {
            try {
                System.out.println("socket error (can't get socket input stream)");
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    private void processReceivedMessage(DtoType type, String message) {
        System.out.println(message);

        switch (type) {
            case LOGIN:
                InitResponse initRes = new InitResponse(message);
                Application.chatRooms = initRes.getChatRooms();
                Application.users = initRes.getUsers();

                LobbyFrame.chatRoomUserListPanel.paintChatUsers(Application.users); // 전체 리스트
                LobbyFrame.chatRoomListPanel.paintChatRoomList();
                break;

            case MESSAGE:
                MessageResponse messageRes = new MessageResponse(message);
                Application.chatPanelMap.get(messageRes.getChatRoomName()).addMessage(messageRes.getMessageType(), messageRes.getUserName(), messageRes.getMessage());
                break;

            case CREATE_CHAT:
                CreateChatRoomResponse createChatRoomResponse = new CreateChatRoomResponse(message);
                String chatRoomName = createChatRoomResponse.getName();

                ChatRoom newChatRoom = new ChatRoom(chatRoomName);
                Application.chatRooms.add(newChatRoom);

                LobbyFrame.chatRoomListPanel.addChatRoomLabel(chatRoomName); // 새로 생성된 채팅방을 채팅방 목록에 추가
                break;

            case USER_LIST:
                UserListResponse userListRes = new UserListResponse(message);
                Application.chatRoomUserListPanelMap.get(userListRes.getChatRoomName()).paintChatUsers(userListRes.getUsers()); // 갱신된 채팅방 사용자 리스트 설정
                break;

            case CHAT_ROOM_LIST:
                ChatRoomListResponse chatRoomListRes = new ChatRoomListResponse(message);
                Application.chatRooms = chatRoomListRes.getChatRooms();
                LobbyFrame.chatRoomListPanel.paintChatRoomList();
                break;
        }
    }
}
