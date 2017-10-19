import javafx.application.Application;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.fxml.FXMLLoader;
//        import javafx.scene.Pane;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

        import java.io.IOException;
//import javafx.fxml.FXMLLoader;

public class MainPage extends Application {

    public Scene scene1,scene2;
    public static Stage window;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        window=primaryStage;
        window.setTitle("CHAIN-REACTION");//set the title as "CHAIN_REACTION"
        //loading the fxml file from scene builder
        AnchorPane pane=(AnchorPane) FXMLLoader.load(MainPage.class.getResource("fxml_files/sample_main.fxml"));
        scene1=new Scene(pane);
        window.setScene(scene1);
        // Display the stage
        window.show();
    }

}
