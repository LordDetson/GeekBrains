package by.babanin.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port = 19999;
    private ServerSocket server;
    private PrintWriter writer;
    private BufferedReader reader;

    public Server() {
        try {
            server = new ServerSocket(port);
            System.out.println("Try to bind to port:" + port);
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server socket is opened");
            Socket socket = server.accept();
            System.out.println("Connection is accepted " + socket);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
    }

    private void acceptMessage() {
        String str;
        try {
            while (reader.ready()) {
                System.out.println(str);
                if (str.equals("bye")) {
                    pw.println("bye");
                    break;
                } else {
                    pw.println("Server is answering:" + str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.close();
        br.close();
        socket.close();
    }
}
