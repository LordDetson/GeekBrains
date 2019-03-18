package by.babanin.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private PrintWriter writer;
    private BufferedReader reader;
    private Socket socket;
    private Console console;

    private Server() {
        try {
            int port = 19999;
            System.out.println("Try to bind to port:" + port);
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server socket is opened");
            socket = server.accept();
            System.out.println("Connection is accepted " + socket);

            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            console = new Console("Client");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void acceptMessage() {
        String str;
        try {
            while (true) {
                str = reader.readLine();
                console.write(str);
                if (str.equals("bye")) {
                    writer.println("bye");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        while (true) {
            writer.println(console.read());
        }
    }

    private void close() {
        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();

        Thread sendMessage = new Thread(server::sendMessage);
        sendMessage.setDaemon(true);
        sendMessage.start();

        server.acceptMessage();

        server.close();
    }
}
