package view.frame;

import app.Application;
import dto.request.ExitChatRequest;
import network.MessageSender;
import view.panel.ChatPanel;
import view.panel.ChatRoomListPanel;
import view.panel.ChatRoomUserListPanel;
import view.panel.MenuPanel;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LobbyFrame extends JFrame implements WindowListener {

    public static MessageSender sender;

    public static ChatPanel chatPanel;

    public static ChatRoomListPanel chatRoomListPanel;

    public static MenuPanel menuPanel;

    public static ChatRoomUserListPanel chatRoomUserListPanel;

    public static CreateChatFrame createChatFrame;

    public LobbyFrame() {
        super("Chat Chat");

        new LoginFrame(this, sender);
        createChatFrame = new CreateChatFrame();

        setLayout(null);
        setSize(830, 550);

        chatPanel = new ChatPanel(this, Application.LOBBY_CHAT_NAME);
        chatRoomUserListPanel = new ChatRoomUserListPanel(this);
        chatRoomListPanel = new ChatRoomListPanel(this);
        menuPanel = new MenuPanel(this, Application.LOBBY_CHAT_NAME);
        menuPanel.setCreateChatBtnVisible(true);

        this.addWindowListener(this);

        setVisible(false);
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }

    public ChatRoomUserListPanel getChatRoomUserListPanel() {
        return chatRoomUserListPanel;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("window opened");
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("window closed");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("window iconified");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("window deiconified");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("window activated");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("window deactivated");
    }
}
