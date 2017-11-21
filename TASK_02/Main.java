package TASK_02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(20000);
        Client client;
        Socket socket;
        int counter = 1;

        Input input = new Input(serverSocket);

        try {
            while (true) {
                socket = serverSocket.accept();
                client = new Client(socket, counter);
                new Thread(client).start();
                counter++;
            }
        } catch (SocketException e) {
        }
        serverSocket.close();
    }
}

