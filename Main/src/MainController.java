import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    void clickPlayGame(ActionEvent event) throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/sample_newgame.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void clickQuit(ActionEvent event) {
//        Stage stage=new Stage();
//        stage.close();
//        rootPane.getChildren().
    }

    @FXML
    void clickSettings(ActionEvent event) throws IOException{
        AnchorPane pane=FXMLLoader.load(getClass().getResource("fxml_files/settings.fxml"));
        rootPane.getChildren().setAll(pane);
    }

}
