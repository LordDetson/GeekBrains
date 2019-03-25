package by.babanin.entity;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private User user;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;

    public Client(User user) {
        try {
            this.user = user;
            int port = 19999;
            System.out.println("Try to open connection:" + port);
            Socket socket = new Socket(InetAddress.getLocalHost(), port);
            System.out.println("Connection is created");
            writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());
            writer.writeUTF(user.getLogin());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUser() {
        return user;
    }

    public void sendMessage(Message message) {
        try {
            writer.writeObject(message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Message acceptMessage() {
        Message message = null;
        try {
            message = (Message) reader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }
}
