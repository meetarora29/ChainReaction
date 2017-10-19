import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class quitController {

//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;

    @FXML
    void clickNo(ActionEvent event) {
        MainController.stage.close();
    }

    @FXML
    void clickYes(ActionEvent event) {
        MainController.stage.close();
        MainPage.window.close();
    }

    @FXML
    void initialize() {

    }
}
