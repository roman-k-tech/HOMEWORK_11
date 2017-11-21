package TASK_02;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Input implements Runnable {

    private Boolean ifPressed = false;
    private ServerSocket serverSocket;

    public Input(ServerSocket serverSocket)
    {
        Thread thread = new Thread(this);
        thread.start();
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {

        System.out.print("To stop server press ENTER:");
        char input = ' ';

        while (input != '\n')
        {
            try
            {
                input = (char) System.in.read();
                System.in.close();
            }
            catch (IOException e) {e.printStackTrace();}
        }


        System.out.print("Server Stopped!");
        try
        {
            serverSocket.close();
        }
        catch (IOException e) {e.printStackTrace();}
    }
}
