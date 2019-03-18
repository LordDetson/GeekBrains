import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //URL url = new File("src/main/java/by/babanin/view/loginPage.fxml").toURL();
        Parent root = FXMLLoader.load(getClass().getResource("view/loginPage.fxml"));
        primaryStage.setTitle("MuChat");
        primaryStage.setScene(new Scene(root, 400, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
