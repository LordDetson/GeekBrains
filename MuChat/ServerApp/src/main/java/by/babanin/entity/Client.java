package by.babanin.entity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private String user;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;

    public Client(String user, ObjectOutputStream writer, ObjectInputStream reader) {
        this.user = user;
        this.writer = writer;
        this.reader = reader;
    }

    public String getUser() {
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
