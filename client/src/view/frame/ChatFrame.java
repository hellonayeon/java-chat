package view.frame;

import view.panel.ChatPanel;
import view.panel.ChatRoomUserListPanel;
import view.panel.MenuPanel;

import javax.swing.*;

public class ChatFrame extends JFrame {

    String chatRoomName;

    ChatPanel chatPanel;

    ChatRoomUserListPanel chatRoomUserListPanel;

    MenuPanel menuPanel;

    public ChatFrame(String chatRoomName) {
        super(chatRoomName);

        setLayout(null);
        setSize(830, 550);

        this.chatRoomName = chatRoomName;
        chatPanel = new ChatPanel(this, chatRoomName);
        chatRoomUserListPanel = new ChatRoomUserListPanel(this);
        menuPanel = new MenuPanel(this, chatRoomName);
        menuPanel.setExitBtnVisible(true);

        setVisible(true);
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }

    public ChatRoomUserListPanel getChatRoomUserListPanel() { return chatRoomUserListPanel; }

}
