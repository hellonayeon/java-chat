package view.frame;

import app.Application;
import domain.User;
import dto.request.LoginRequest;
import network.MessageSender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    LobbyFrame lobbyFrame;

    JLabel idLabel = new JLabel("아이디 ");

    JLabel nameLabel = new JLabel("이름 ");

    JTextField idTextF = new JTextField(20);

    JTextField nameTextF = new JTextField(20);

    JButton loginBtn = new JButton("로그인");

    public LoginFrame(LobbyFrame lobbyFrame, MessageSender sender) {
        this.lobbyFrame = lobbyFrame;

        // 로그인 프레임 설정
        setLayout(null);

        // 레이블, 텍스트 입력창 설정
        idLabel.setBounds(100, 50, 100, 50);
        add(idLabel);

        idTextF.setBounds(200, 50, 300, 50);
        add(idTextF);

        nameLabel.setBounds(100, 120, 100, 50);
        add(nameLabel);

        nameTextF.setBounds(200, 120, 300, 50);
        add(nameTextF);

        loginBtn.setBounds(150, 220, 300, 50);
        loginBtn.addActionListener(this);
        add(loginBtn);

        setSize(600, 400);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String id = idTextF.getText();
        String name = nameTextF.getText();

        if (id.trim().isEmpty()) {
            System.out.println("아이디 공백");
        }
        if (name.trim().isEmpty()) {
            System.out.println("이름 입력");
        }

        // TODO 아이디 이름 형식 검사
        User user = new User(id, name);

        Application.me = user;
        Application.users.add(user);

        // 사용자 정보 서보에 전송
        Application.sender.sendMessage(new LoginRequest(id, name));

        // 화면 세팅
        this.dispose();
        lobbyFrame.setVisible(true);
    }

}
