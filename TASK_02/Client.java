package TASK_02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

    private Socket socket;
    private int counter;

    public Client (Socket socket, int counter)
    {
        this.socket = socket;
        this.counter = counter;
    }

    @Override
    public void run() {
        try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true))
        {
            printWriter.println("Client number: " + counter);
            printWriter.println("Available processors (cores): " + Runtime.getRuntime().availableProcessors());
            printWriter.println("Free memory (bytes): " + Runtime.getRuntime().freeMemory());
            printWriter.println("OS: " + System.getProperty("os.name"));
        }
        catch (IOException e) {e.printStackTrace();}
        finally
        {
            try {
                socket.close();
            }
            catch (IOException e) {e.printStackTrace();}
        }
    }
}
