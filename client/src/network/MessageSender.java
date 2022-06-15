package network;

import dto.request.*;
import dto.type.DtoType;

import java.io.PrintWriter;
import java.net.Socket;

public class MessageSender {

    Socket socket;

    public MessageSender(Socket socket) {
        this.socket = socket;
    }

    public void sendMessage(DTO dto) {
        DtoType type = dto.getType();

        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            switch (type) {
                case LOGIN:
                    writer.println((LoginRequest) dto);
                    break;
                case CREATE_CHAT:
                    writer.println((CreateChatRoomRequest) dto);
                    break;
                case ENTER_CHAT:
                    writer.println((EnterChatRequest) dto);
                    break;
                case EXIT_CHAT:
                    writer.println((ExitChatRequest) dto);
                    break;
                case MESSAGE:
                    writer.println((MessageRequest) dto);
                    break;
            }

            writer.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}

