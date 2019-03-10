package by.babanin.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ChatController {

    @FXML
    private TextField messageField;

    @FXML
    private TextArea chatArea;

    @FXML
    private ImageView messageButton;

    @FXML
    void initialize() {
        messageButton.setOnMouseClicked(event -> {
            doMessage();
        });

        messageField.setOnAction(event -> {
            doMessage();
        });
    }

    private void doMessage() {
        String message;
        if (!(message = messageField.getText().trim()).isEmpty()) {
            chatArea.appendText(message + "\n\n\r");
            messageField.setText("");
        }
    }
}
