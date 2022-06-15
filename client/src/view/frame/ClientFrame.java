package view.frame;

import network.MessageSender;
import view.panel.ChatPanel;
import view.panel.ChatRoomListPanel;
import view.panel.MenuPanel;
import view.panel.ChatRoomUserListPanel;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.Socket;

public class ClientFrame extends JFrame implements WindowListener {

    public static MessageSender sender;

    public static ChatPanel chatPanel;

    public static ChatRoomListPanel chatRoomListPanel;

    public static MenuPanel menuPanel;

    public static ChatRoomUserListPanel chatRoomUserListPanel;

    public static CreateChatFrame createChatFrame;

    public ClientFrame() {
        super("채팅");

        new LoginFrame(this, sender);
        createChatFrame = new CreateChatFrame();

        setLayout(null);
        setSize(830, 550);

        chatPanel = new ChatPanel(this);
        chatRoomUserListPanel = new ChatRoomUserListPanel(this);
        chatRoomListPanel = new ChatRoomListPanel(this);
        menuPanel = new MenuPanel(this);

        setVisible(false);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("window opened");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("window closing");
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
