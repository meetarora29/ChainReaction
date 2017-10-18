import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
public class NewGAme extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public Scene scene2;
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Play Game");
        AnchorPane pane=(AnchorPane)FXMLLoader.load(NewGAme.class.getResource("sample_newgame.fxml"));
         scene2=new Scene(pane);
        primaryStage.setScene(scene2);
        // Display the stage
        primaryStage.show();
    }
}
