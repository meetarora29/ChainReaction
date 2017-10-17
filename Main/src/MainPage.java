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
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("CHAIN-REACTION");//set the title as "CHAIN_REACTION"
        //loading the fxml file from scene builder
        AnchorPane pane=(AnchorPane) FXMLLoader.load(MainPage.class.getResource("sample_main.fxml"));
//        BorderPane pane=new BorderPane();
//        Button btn=new Button("Play Game");
//        Button btn1=new Button("Settings");
//        Button btn2=new Button("Quit");
//        pane.setCenter(btn);
//        pane.setCenter(btn1);
//        pane.setCenter(btn2);
        scene1=new Scene(pane);
        primaryStage.setScene(scene1);
        // Display the stage
        primaryStage.show();
    }
//    @Override
//    public void handle(ActionEvent event)
//    {
//
//    }

}
