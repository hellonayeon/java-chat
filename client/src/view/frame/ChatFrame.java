package view.frame;

import network.MessageSender;
import view.panel.ChatPanel;
import view.panel.ChatRoomListPanel;
import view.panel.ChatRoomUserListPanel;
import view.panel.MenuPanel;

import javax.swing.*;
import java.net.Socket;

public class ChatFrame extends JFrame {

    String chatName;

    public static ChatPanel chatPanel;

    public static ChatRoomUserListPanel chatRoomUserListPanel;

    public ChatFrame(String chatName) {
        super(chatName);

        setLayout(null);
        setSize(830, 550);

        this.chatName = chatName;
        chatPanel = new ChatPanel(this);
        chatRoomUserListPanel = new ChatRoomUserListPanel(this);


        setVisible(true);
    }

}
