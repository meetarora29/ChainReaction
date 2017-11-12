import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;


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
    void clickNo1(javafx.scene.input.KeyEvent event) {
        if(event.getCode()== KeyCode.ENTER) {
            GameOverController.stage1.close();
        }
    }
    @FXML
    void clickYes(ActionEvent event) {
        MainPage.window.close();
        Grid.stage.close();
        GameOverController.stage1.close();
    }
    @FXML
    void clickYes1(javafx.scene.input.KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER) {
            MainPage.window.close();
            Grid.stage.close();
            GameOverController.stage1.close();
        }
    }

//



}
