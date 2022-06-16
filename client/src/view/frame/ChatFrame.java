package view.frame;

import app.Application;
import dto.request.ExitChatRequest;
import view.panel.ChatPanel;
import view.panel.ChatRoomUserListPanel;
import view.panel.MenuPanel;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ChatFrame extends JFrame implements WindowListener {

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

        addWindowListener(this);

        setVisible(true);
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }

    public ChatRoomUserListPanel getChatRoomUserListPanel() { return chatRoomUserListPanel; }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
