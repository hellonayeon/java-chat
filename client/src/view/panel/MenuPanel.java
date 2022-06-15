package view.panel;

import view.frame.ClientFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    JButton createChatBtn = new JButton("채팅방 생성");

    JButton exitBtn = new JButton("나가기");

    public MenuPanel(JFrame frame) {
        setLayout(null);

        createChatBtn.setBounds(10, 10, 100, 35);
        createChatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientFrame.createChatFrame.setVisible(true);
            }
        });
        add(createChatBtn);

        exitBtn.setBounds(110, 10, 100, 35);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientFrame.createChatFrame.setVisible(false);
            }
        });
        add(exitBtn);

        frame.add(this);

        setBackground(Color.GRAY);
        setBounds(410, 460, 400, 50);
        setVisible(true);
    }


}
