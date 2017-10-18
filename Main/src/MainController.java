import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    void clickPlayGame(ActionEvent event) throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("sample_newgame.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void clickQuit(ActionEvent event) {
        
    }

    @FXML
    void clickSettings(ActionEvent event) {

    }

}
