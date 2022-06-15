package view.panel;

import domain.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChatRoomUserListPanel extends JPanel {

    JPanel labelPanel = new JPanel();

    public ChatRoomUserListPanel(JFrame frame) {
        setLayout(null);

        // 채팅 메시지 영역 (스크롤)
        labelPanel.setSize(400, 200);
        labelPanel.setBackground(Color.MAGENTA);
        labelPanel.setLayout(new GridLayout(50, 1));

        JScrollPane scrPane = new JScrollPane(labelPanel);
        scrPane.setBounds(0, 0, 400, 200);
        scrPane.setBackground(Color.MAGENTA);
        add(scrPane);

        frame.add(this);

        setBackground(Color.GREEN);
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
