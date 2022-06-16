package view.panel;

import app.Application;
import dto.request.MessageRequest;
import dto.type.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel implements ActionListener {

    String chatRoomName;

    JTextArea chatTextA = new JTextArea();

    JTextField msgTextF = new JTextField(50);

    JButton sendBtn = new JButton("전송");


    public ChatPanel(JFrame frame, String chatRoomName) {
        setLayout(null);

        this.chatRoomName = chatRoomName;

        // 채팅 메시지 영역 (스크롤)
        chatTextA.setEditable(false);
        JScrollPane scrPane = new JScrollPane(chatTextA);
        scrPane.setBounds(10, 0, 380, 450);
        add(scrPane);

        // 메시지 입력 및 전송 영역

        msgTextF.setBounds(10, 460, 250, 35);
        add(msgTextF);

        sendBtn.setBounds(270, 460, 120, 35);
        sendBtn.addActionListener(this);
        add(sendBtn);

        frame.add(this);

        setBounds(10, 10, 400, 500);
    }

    public void addMessage(MessageType messageType, String userName, String message) {
        String msg = "";
        switch (messageType) {
            case ENTER:
            case EXIT:
                msg = message;
                break;
            case CHAT:
                msg = "[" + userName + "] 님이 보낸 메시지: " + message;
                break;

        }
        chatTextA.append(msg + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message = msgTextF.getText();

        if (!message.isEmpty()) {
            Application.sender
                    .sendMessage(new MessageRequest(MessageType.CHAT, chatRoomName, Application.me.getName(), message));
            chatTextA.append("내가 보낸 메시지: " + message + "\n");
        }
        msgTextF.setText("");
    }
}
