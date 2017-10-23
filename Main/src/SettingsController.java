import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;

public class SettingsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ColorPicker color;

    @FXML
    void clickDone(ActionEvent event) {

    }

    @FXML
    void selectColor(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert color != null : "fx:id=\"color\" was not injected: check your FXML file 'settings.fxml'.";

    }
}
