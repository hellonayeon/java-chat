package network;

import dto.request.*;
import dto.type.MessageType;

import java.io.PrintWriter;
import java.net.Socket;

public class MessageSender {

    Socket socket;

    public MessageSender(Socket socket) {
        this.socket = socket;
    }

    public void sendMessage(DTO dto) {
        MessageType type = dto.getType();

        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            switch (type) {
                case LOGIN:
                    writer.println((LoginDto) dto);
                    break;
                case CREATE_CHAT:
                    writer.println((CreateChatRoomDto) dto);
                    break;
                case ENTER_CHAT:
                    writer.println((EnterChatDto) dto);
                    break;
                case EXIT_CHAT:
                    writer.println((ExitChatDto) dto);
                    break;
                case SEND_MESSAGE:
                    writer.println((SendMessageDto) dto);
                    break;
            }

            writer.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}

