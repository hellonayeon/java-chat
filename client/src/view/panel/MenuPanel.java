package view.panel;

import app.Application;
import dto.request.ExitChatRequest;
import view.frame.LobbyFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    String chatRoomName;

    JButton createChatBtn = new JButton("새로운 채팅방 생성");

    JButton exitBtn = new JButton("나가기");

    public MenuPanel(JFrame frame, String chatRoomName) {
        setLayout(null);

        this.chatRoomName = chatRoomName;

        createChatBtn.setBounds(15, 10, 370, 35);
        createChatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("chat room create btn clicked");
                LobbyFrame.createChatFrame.setVisible(true);
            }
        });
        add(createChatBtn);
        createChatBtn.setVisible(false); // 로비 채팅방, 생성된 채팅방에 따라 버튼 show, hide

        exitBtn.setBounds(15, 10, 370, 35);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("chat room exit btn clicked");
                System.out.println("chat room = [" + chatRoomName + "] chat frame closed.");

                // 채팅창이 닫히면 사용자 나가기 처리
                Application.chatPanelMap.remove(chatRoomName);
                Application.chatRoomUserListPanelMap.remove(chatRoomName);
                Application.sender.sendMessage(new ExitChatRequest(chatRoomName, Application.me.getId()));

                frame.dispose(); // 채팅 화면 닫기
            }
        });
        add(exitBtn);
        createChatBtn.setVisible(false);

        frame.add(this);

        setBounds(410, 460, 400, 50);
        setVisible(true);
    }

    public void setCreateChatBtnVisible(boolean bool) {
        createChatBtn.setVisible(bool);
    }

    public void setExitBtnVisible(boolean bool) {
        exitBtn.setVisible(bool);
    }
}
