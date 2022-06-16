package view.panel;

import domain.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChatRoomUserListPanel extends JPanel {

    JPanel labelPanel = new JPanel();

    JLabel label = new JLabel("사용자 목록");

    public ChatRoomUserListPanel(JFrame frame) {
        setLayout(null);

        label.setBounds(0, 0, 400, 50);
        add(label);

        // 채팅 메시지 영역 (스크롤)
        labelPanel.setSize(400, 150);
        labelPanel.setLayout(new GridLayout(50, 1));

        JScrollPane scrPane = new JScrollPane(labelPanel);
        scrPane.setBounds(0, 50, 400, 150);
        add(scrPane);

        frame.add(this);

        setBounds(410, 10, 400, 200);
    }

    public void paintChatUsers(List<User> chatUsers) {
        labelPanel.removeAll();

        for (User user : chatUsers) {
            labelPanel.add(new Label(user.getName()));
        }

        revalidate();
    }
}
