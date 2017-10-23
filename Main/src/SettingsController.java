import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;

public class SettingsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane settingsPane;

    @FXML
    private ColorPicker color1;

    @FXML
    private ColorPicker color2;

    @FXML
    private ColorPicker color3;

    @FXML
    private ColorPicker color4;

    @FXML
    private ColorPicker color5;

    @FXML
    private ColorPicker color6;

    @FXML
    private ColorPicker color7;

    @FXML
    private ColorPicker color8;

    @FXML
    void clickDone(ActionEvent event) throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/sample_main.fxml"));
        settingsPane.getChildren().setAll(pane);
    }

    @FXML
    void selectColor1(ActionEvent event) {

    }

    @FXML
    void selectColor2(ActionEvent event) {

    }

    @FXML
    void selectColor3(ActionEvent event) {

    }

    @FXML
    void selectColor4(ActionEvent event) {

    }

    @FXML
    void selectColor5(ActionEvent event) {

    }

    @FXML
    void selectColor6(ActionEvent event) {

    }

    @FXML
    void selectColor7(ActionEvent event) {

    }

    @FXML
    void selectColor8(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert settingsPane != null : "fx:id=\"settingsPane\" was not injected: check your FXML file 'settings.fxml'.";
        assert color1 != null : "fx:id=\"color1\" was not injected: check your FXML file 'settings.fxml'.";
        assert color2 != null : "fx:id=\"color2\" was not injected: check your FXML file 'settings.fxml'.";
        assert color3 != null : "fx:id=\"color3\" was not injected: check your FXML file 'settings.fxml'.";
        assert color4 != null : "fx:id=\"color4\" was not injected: check your FXML file 'settings.fxml'.";
        assert color5 != null : "fx:id=\"color5\" was not injected: check your FXML file 'settings.fxml'.";
        assert color6 != null : "fx:id=\"color6\" was not injected: check your FXML file 'settings.fxml'.";
        assert color7 != null : "fx:id=\"color7\" was not injected: check your FXML file 'settings.fxml'.";
        assert color8 != null : "fx:id=\"color8\" was not injected: check your FXML file 'settings.fxml'.";

    }
}
