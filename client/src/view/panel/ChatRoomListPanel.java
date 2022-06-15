package view.panel;

import app.Application;
import domain.ChatRoom;
import dto.request.EnterChatDto;
import view.frame.ChatFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChatRoomListPanel extends JPanel {

    JPanel labelPanel;

    public ChatRoomListPanel(JFrame frame) {
        setLayout(null);

        // 채팅 메시지 영역 (스크롤)
        labelPanel = new JPanel();
        labelPanel.setSize(400, 250);
        labelPanel.setBackground(Color.MAGENTA);
        labelPanel.setLayout(new GridLayout(30, 1));
        for (int i=0; i<30; i++) {
            labelPanel.add(new JLabel("Hell" + i));
        }

        JScrollPane scrPane = new JScrollPane(labelPanel);
        scrPane.setBounds(0, 0, 400, 250);
        scrPane.setBackground(Color.MAGENTA);
        add(scrPane);

        frame.add(this);

        setBackground(Color.BLUE);
        setBounds(410, 210, 400, 250);
    }

    public void addChatRoomLabel() {
        labelPanel.removeAll();

        for (String key : Application.chatMap.keySet()) {
            ChatRoom chatRoom = Application.chatMap.get(key);
            JLabel label = new JLabel(chatRoom.getName() + "(" + chatRoom.getId() + ")");
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Application.sender.sendMessage(new EnterChatDto(chatRoom.getId(), Application.me.getId()));
                    new ChatFrame(chatRoom.getName());
                }
            });
            labelPanel.add(label);
        }

        revalidate();
    }
}
