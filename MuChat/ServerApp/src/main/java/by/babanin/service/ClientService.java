package by.babanin.service;

import by.babanin.entity.Client;
import by.babanin.entity.Message;

import java.util.Map;

public class ClientService {
    private final Client client;
    private final Map<String, Client> clientMap;

    public ClientService(Client client, Map<String, Client> clientMap) {
        this.client = client;
        this.clientMap = clientMap;
    }

    public void processMessage() {
        while (true) {
            Message message = client.acceptMessage();
            System.out.printf("received message %s to %s\n", message.toString(), client.getUser());
            if (message.getTo().equals("All")) {
                clientMap.forEach((s, client1) -> client1.sendMessage(message));
            } else {
                clientMap.get(message.getFrom()).sendMessage(message);
                clientMap.get(message.getTo()).sendMessage(message);
            }
        }
    }
}
