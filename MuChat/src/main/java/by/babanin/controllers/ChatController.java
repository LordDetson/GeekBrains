package by.babanin.controllers;

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
        client = new Client();
        formatter = new Formatter();
        fmtString = "Server : %s %s";
        locale = new Locale("ru", "RU");
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd.LL.yy HH:mm:ss", locale);

        messageButton.setOnMouseClicked(event -> doMessage());
        messageField.setOnAction(event -> doMessage());

        initReceiver();
    }

    private void doMessage() {
        String message;
        if (!(message = messageField.getText().trim()).isEmpty()) {
            chatArea.appendText(message + "\n\n\r");
            messageField.setText("");
            client.sendMessage(message);
        }
    }

    private void initReceiver() {
        System.out.println();
        Thread thread = new Thread(() -> {
            String echoMessage;
            while (true) {
                echoMessage = client.acceptMessage();
                LocalDateTime dateTime = LocalDateTime.now();
                formatter.format(fmtString, echoMessage, dateTime.format(dateTimeFormatter));
                chatArea.appendText(formatter + "\n\n\r");
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("receiver started");
    }
}
