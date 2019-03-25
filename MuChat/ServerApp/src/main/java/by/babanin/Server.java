package by.babanin;

import by.babanin.dao.DBHandler;
import by.babanin.entity.Client;
import by.babanin.entity.Message;
import by.babanin.entity.User;
import by.babanin.service.ClientService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private Map<String, Client> clientMap = Collections.synchronizedMap(new HashMap<>());

    private ObjectOutputStream writer;
    private ObjectInputStream reader;
    private Socket socket;
    private Console console;

    private User defaultUser = DBHandler.getInstance().getUser("admin");

    private Server() {
        try {
            int port = 19999;
            System.out.println("Try to bind to port:" + port);
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server socket is opened");
            while (true) {
                socket = server.accept();

                writer = new ObjectOutputStream(socket.getOutputStream());
                reader = new ObjectInputStream(socket.getInputStream());

                Client client = new Client(reader.readUTF(), writer, reader);
                System.out.println("client connected::" + client.getUser() + "::" + socket);
                clientMap.put(client.getUser(), client);

                new Thread(() -> new ClientService(client, clientMap).processMessage()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
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

        server.close();
    }
}
