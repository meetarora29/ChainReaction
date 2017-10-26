/**
 * Sample Skeleton for 'game_end.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOverController {

//    @FXML // ResourceBundle that was given to the FXMLLoader
//    private ResourceBundle resources;
//
//    @FXML // URL location of the FXML file that was given to the FXMLLoader
//    private URL location;
    @FXML
    private AnchorPane gamePane;
    @FXML
    void clickNewGame(ActionEvent event) throws IOException{
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/selectplayer.fxml"));
        gamePane.getChildren().setAll(pane);
    }

    @FXML
    void clickQuit(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Quit");
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/quitGame.fxml"));
        stage.setScene(new Scene(pane));
        stage.show();
    }

    @FXML
    void clickRestart(ActionEvent event) {
        
    }

//    @FXML // This method is called by the FXMLLoader when initialization is complete
//    void initialize() {
//
//    }
}
