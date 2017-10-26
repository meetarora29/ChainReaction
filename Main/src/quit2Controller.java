import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class quit2Controller {

//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;

    @FXML
    void clickNo(ActionEvent event) {
        GameOverController.stage1.close();

    }

    @FXML
    void clickYes(ActionEvent event) {
        GamePage.stage.close();
        Grid.stage.close();
        GameOverController.stage1.close();
    }

//    @FXML
//    void initialize() {
//
//    }
}
