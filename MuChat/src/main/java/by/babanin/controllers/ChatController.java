package by.babanin.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ChatController {

    private SimpleClient client = new SimpleClient();

    @FXML
    private TextField messageField;

    @FXML
    private TextArea chatArea;

    @FXML
    private ImageView messageButton;

    @FXML
    void initialize() {
        messageButton.setOnMouseClicked(event -> {
            String message = doMessage();
            client.sendMessage(message);
        });

        messageField.setOnAction(event -> {
            String message = doMessage();
            client.sendMessage(message);
        });
        initReceiver();
    }

    private String doMessage() {
        String message;
        if (!(message = messageField.getText().trim()).isEmpty()) {
            chatArea.appendText(message + "\n\n\r");
            messageField.setText("");
        }
        return message;
    }

    private void initReceiver() {
        System.out.println();
        Thread thread = new Thread(() -> {
            while (true) {
                String echoMessage = client.acceptMessage();
                chatArea.appendText(echoMessage + "\n\n\r");
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("receiver started");
    }
}
