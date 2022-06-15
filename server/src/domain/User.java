package domain;

import dto.request.LoginUserDto;

import java.net.Socket;
import java.util.Date;

public class User {

    private Socket socket; // 현재 입장한 채팅방의 소켓

    private String id; // 아이디: 사용자 식별자

    private String name; // 이름: 채팅방에서 사용되는 이름

    private Date createdAt; // 로그인 시점

    public User(Socket socket, LoginUserDto dto) {
        this.socket = socket;
        this.id = dto.getId();
        this.name = dto.getName();

        this.createdAt = new Date();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getEnterString() {
        return name + " 님이 입장하셨습니다.";
    }
}
