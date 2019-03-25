package by.babanin.controllers;

import by.babanin.dao.DBHandler;
import by.babanin.entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegistrationController {

    @FXML
    private Button registrationButton;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField loginField;

    @FXML
    private RadioButton maleRadioBox;

    @FXML
    private RadioButton femaleRadioBox;

    @FXML
    void initialize() {
        DBHandler handler = DBHandler.getInstance();
        registrationButton.setOnAction(event -> createNewUser(handler));
    }

    private void createNewUser(DBHandler handler) {
        User newUser = new User(
                firstNameField.getText(),
                lastNameField.getText(),
                loginField.getText(),
                passwordField.getText(),
                maleRadioBox.isSelected() ? "male" : "female"
        );
        handler.registrationUser(newUser);
    }
}
