import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
    void clickNo1(KeyEvent event) throws IOException {

//        if(MainPage.game_count==1)
        if(event.getCode()== KeyCode.ENTER) {
            MainController.stage.close();
        }
    }
    @FXML
    void clickYes(ActionEvent event) throws IOException {

//        if(MainPage.game_count==1)

        Grid grid=GamePage.getGrid();
        if(grid!=null && grid.isNotEnded())

        {
            GamePage gamePage=new GamePage();
            gamePage.serialize();
        }
        MainController.stage.close();
        MainPage.window.close();
    }
    @FXML
    void clickYes1(KeyEvent event) throws IOException {

//        if(MainPage.game_count==1)
        if(event.getCode()== KeyCode.ENTER) {
            Grid grid = GamePage.getGrid();
            if (grid != null && !(grid.checkWin() && grid.getFlag() != 0 && grid.noAnimation()))

            {
                GamePage gamePage = new GamePage();
                gamePage.serialize();
            }
            MainController.stage.close();
            MainPage.window.close();
        }
    }

    @FXML
    void initialize() {

    }
}
