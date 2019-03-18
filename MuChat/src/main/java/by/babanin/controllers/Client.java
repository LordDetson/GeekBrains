package by.babanin.controllers;

import java.io.*;

import javafx.scene.control.TextArea;

import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.StringJoiner;

public class Client {
    private int port = 19999;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;


    public Client() {
        try {
            System.out.println("Try to open connection:" + port);
            socket = new Socket(InetAddress.getLocalHost(), port);
            System.out.println("Connection is created");
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public String acceptMessage() {
        String str = null;
        try {
            str = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
