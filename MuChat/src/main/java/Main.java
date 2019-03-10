package by.babanin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(getClass().getResource("by/babanin/view/loginPage.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("by/babanin/view/loginPage.fxml"));
        primaryStage.setTitle("MuChat");
        primaryStage.setScene(new Scene(root, 400, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
