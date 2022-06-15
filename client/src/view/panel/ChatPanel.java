package view.panel;

import app.Application;
import dto.request.SendMessageDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel implements ActionListener {

    JTextArea chatTextA = new JTextArea();

    JTextField msgTextF = new JTextField();

    JButton sendBtn = new JButton("전송");


    public ChatPanel(JFrame frame) {
        setLayout(null);

        // 채팅 메시지 영역 (스크롤)
        JScrollPane scrPane = new JScrollPane(chatTextA);
        scrPane.setBounds(10, 0, 380, 450);
        add(scrPane);

        // 메시지 입력 및 전송 영역

        msgTextF.setBounds(10, 460, 250, 35);
        add(msgTextF);

        sendBtn.setBounds(270, 460, 120, 35);
        sendBtn.setBackground(Color.GREEN);
        sendBtn.addActionListener(this);
        add(sendBtn);

        frame.add(this);

        setBackground(Color.RED);
        setBounds(10, 10, 400, 500);
    }

    public void addMessage(String message) {
        chatTextA.append(message + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message = msgTextF.getText();

        if (!message.isEmpty()) {
            Application.sender
                    .sendMessage(new SendMessageDto(Application.curChatRoomId, Application.me.getId(), message));
            chatTextA.append(message + "\n");
        }
        msgTextF.setText("");
    }
}
