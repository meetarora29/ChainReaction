import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class NewGameController2 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane newgamepane;

    @FXML
    void clickBack(ActionEvent event) {

    }

    @FXML
    void clickNewGame(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert newgamepane != null : "fx:id=\"newgamepane\" was not injected: check your FXML file 'newGame2.fxml'.";

    }
}
