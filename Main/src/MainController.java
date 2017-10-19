import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    public static Stage stage;
    @FXML
    private AnchorPane rootPane;

    @FXML
    void clickPlayGame(ActionEvent event) throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/sample_newgame.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void clickQuit(ActionEvent event) throws IOException {
        stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Quit");
        AnchorPane pane=FXMLLoader.load(getClass().getResource("fxml_files/quitGame.fxml"));
        stage.setScene(new Scene(pane));
        stage.show();
//        MainPage.window.close();
    }

    @FXML
    void clickSettings(ActionEvent event) throws IOException{
        AnchorPane pane=FXMLLoader.load(getClass().getResource("fxml_files/settings.fxml"));
        rootPane.getChildren().setAll(pane);
    }

}
