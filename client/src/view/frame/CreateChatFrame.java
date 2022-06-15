package view.frame;

import app.Application;
import dto.request.CreateChatRoomDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateChatFrame extends JFrame {

    CreateChatFrame frame;

    JLabel chatNameLabel = new JLabel("채팅방 이름");

    JTextField chatNameTextF = new JTextField();

    JButton okBtn = new JButton("확인");

    JButton cancelBtn = new JButton("취소");

    public CreateChatFrame() {
        setLayout(null);

        frame = this;

        chatNameLabel.setBounds(100, 50, 100, 50);
        add(chatNameLabel);

        chatNameTextF.setBounds(200, 50, 300, 50);
        add(chatNameTextF);

        okBtn.setBounds(100, 220, 150, 50);
        okBtn.addActionListener(new OkBtnActionListener());
        add(okBtn);

        cancelBtn.setBounds(260, 220, 150, 50);
        cancelBtn.addActionListener(new CancelBtnActionListener());
        add(cancelBtn);

        setSize(600, 400);
        setVisible(false);
    }

    class OkBtnActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String chatName = chatNameTextF.getText();
            if (chatName.isEmpty()) {
                return;
            }

            Application.sender.sendMessage(new CreateChatRoomDto(chatName, Application.me.getId()));
            frame.dispose();

            new ChatFrame(chatName);
        }
    }

    class CancelBtnActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    }
}


