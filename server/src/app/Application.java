package app;

import dao.ChatDao;
import service.ChatService;
import thread.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static List<Socket> sockets = new ArrayList<>();

    ChatDao chatDao = new ChatDao();

    ChatService chatService = new ChatService(chatDao);

    public Application() {
        Socket clientSocket = null;

        try {
            ServerSocket serverSocket = new ServerSocket(9000);

            while (true) {
                System.out.println("접속 대기중...");
                clientSocket = serverSocket.accept();
                System.out.println("client IP: " + clientSocket.getInetAddress() + " Port: " + clientSocket.getPort());

                sockets.add(clientSocket);

                ServerThread thread = new ServerThread(clientSocket, chatService);
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
