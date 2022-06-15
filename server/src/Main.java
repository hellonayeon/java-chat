import app.Application;
import domain.User;
import thread.ClientExitException;
import thread.ServerThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        new Application();

    }
}
