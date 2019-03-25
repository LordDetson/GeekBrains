package by.babanin.controllers;

import by.babanin.entity.Client;
import by.babanin.entity.Message;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Locale;

public class ChatController {

    private Client client;
    private Formatter formatter;
    private String fmtString;
    private Locale locale;
    private DateTimeFormatter dateTimeFormatter;

    @FXML
    private TextField messageField;

    @FXML
    private TextArea chatArea;

    @FXML
    private ImageView messageButton;

    @FXML
    void initialize() {
        client = new Client(Controller.getLogedUser());
        formatter = new Formatter();
        fmtString = "%s%s:\n%s\n%s\n\n\r";
        locale = new Locale("ru", "RU");
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd.LL.yy HH:mm:ss", locale);

        messageButton.setOnMouseClicked(event -> sendMessage());
        messageField.setOnAction(event -> sendMessage());

        initReceiver();
    }

    private void sendMessage() {
        String message;
        if (!(message = messageField.getText().trim()).isEmpty()) {
            Message mes;
            if ((mes = Message.parse(message, client.getUser())) != null) {
                client.sendMessage(mes);
                /*LocalDateTime dateTime = LocalDateTime.now();
                formatter.format(fmtString, Message.isAll(mes) ? "" : "[/w] ", mes.getFrom(),
                        mes.getMessage(),
                        dateTime.format(dateTimeFormatter));
                chatArea.appendText(formatter.toString());*/
            }
            messageField.setText("");
        }
    }

    private void initReceiver() {
        System.out.println();
        Thread thread = new Thread(() -> {
            Message message;
            while (true) {
                message = client.acceptMessage();
                LocalDateTime dateTime = LocalDateTime.now();
                formatter.format(fmtString, Message.isAll(message) ? "" : "[/w] ", message.getFrom(),
                        message.getMessage(), dateTime.format(dateTimeFormatter));
                chatArea.appendText(formatter.toString());
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("receiver started");
    }
}
