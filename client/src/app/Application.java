package app;

import domain.ChatRoom;
import domain.User;
import network.MessageReceiver;
import network.MessageSender;
import view.frame.ChatFrame;
import view.frame.LobbyFrame;
import view.panel.ChatPanel;
import view.panel.ChatRoomUserListPanel;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    public static Socket socket;

    public static MessageSender sender;

    public static MessageReceiver receiver;

    public static LobbyFrame lobbyFrame;

    public static User me;

    public static List<User> users = new ArrayList<>(); // 현재 접속 중인 모든 사용자 리스트

    public static List<ChatRoom> chatRooms = new ArrayList<>(); // 채팅방 목록 [key] 채팅방 이름, [value] 채팅방 객체 리스트 (채팅방 이름 중복 가능)

    public static Map<String, ChatPanel> chatPanelMap = new HashMap<>();// 채팅방 프레임 관리, 메시지 왔을 때 어떤 채팅방 레이블을 갱신해야 하는지 확인

    public static Map<String, ChatRoomUserListPanel> chatRoomUserListPanelMap = new HashMap<>();

    public static final String LOBBY_CHAT_NAME = "Lobby"; // 로비 채팅방 이름

    public Application() {
        try {
            // 애플리케이션 시작 시 소켓 연결
            socket = new Socket("192.168.0.99", 9000);
            System.out.println("connect success to chat server");

            sender = new MessageSender(socket);
            receiver = new MessageReceiver(socket);
            receiver.start();

            lobbyFrame = new LobbyFrame();
            chatPanelMap.put(LOBBY_CHAT_NAME, lobbyFrame.getChatPanel());
            chatRoomUserListPanelMap.put(LOBBY_CHAT_NAME, lobbyFrame.getChatRoomUserListPanel());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
