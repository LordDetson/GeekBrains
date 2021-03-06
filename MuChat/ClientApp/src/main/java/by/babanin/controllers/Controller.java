package by.babanin.controllers;

import by.babanin.animation.Shake;
import by.babanin.dao.DBHandler;
import by.babanin.entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    private static User logedUser;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button registrationButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    @FXML
    void initialize() {
        loginButton.setOnAction(event -> {
            String login = loginField.getText().trim();
            String password = passwordField.getText();
            if (!login.isEmpty() && !password.isEmpty()) {
                if (loginUser(login, password)) {
                    openPage("/view/chatPage.fxml");
                } else {
                    Shake loginFieldNotCorrectAnimation = new Shake(loginField);
                    Shake passwordFieldNotCorrectAnimation = new Shake(passwordField);
                    loginFieldNotCorrectAnimation.doAnimation();
                    passwordFieldNotCorrectAnimation.doAnimation();
                    System.err.println("Login or password aren't correct!");
                }
            } else {
                System.err.println("Login or password are empty!");
            }
        });

        registrationButton.setOnAction(event -> {
            openPage("/view/registrationPage.fxml");
        });
    }

    private boolean loginUser(String login, String password) {
        DBHandler dbHandler = DBHandler.getInstance();
        if ((logedUser = dbHandler.getUser(login, password)) != null) {
            return true;
        }
        return false;
    }

    private void openPage(String pagePath) {
        registrationButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(pagePath));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public static User getLogedUser() {
        return logedUser;
    }
}
