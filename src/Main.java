import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        LoginFrameOne frame = new LoginFrameOne();
        frame.setVisible(true);

        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            ArrayList<Client> clients = new ArrayList<>();

            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    Client client = new Client(socket, clients);
                    clients.add(client);
                    Thread clientThread = new Thread(client);
                    clientThread.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

