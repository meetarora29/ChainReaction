import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * This class is the controller class for exiting the game
 * It asks the user to confirm the quit
 * if they press the YES button
 * This class is active when the game has not started yet and the user decides to exit
 * @author Gagandeep Singh-2016037
 */
public class quitController {

//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;

    /**
     * onAction function of the no button
     * it closes the quit window
     * @param event
     */
    @FXML
    void clickNo(ActionEvent event) {
        MainController.stage.close();
    }

    /**
     * onKeyPressed function of the no button
     * it closes the quit window
     * @param event
     */
    @FXML
    void clickNo1(KeyEvent event) throws IOException {

        //only when ENTER key is pressed
        if(event.getCode()== KeyCode.ENTER) {
            MainController.stage.close();
        }
    }

    /**
     * onAction function of YES button
     * @param event
     * @throws IOException
     */
    @FXML
    void clickYes(ActionEvent event) throws IOException {

        Grid grid=GamePage.getGrid();
        //Checking if the game is finished or not and if there is a grid that is not destroyed if(grid!=null && grid.isNotEnded())
        if(grid!=null && grid.isNotEnded())
        {
            //saving the game if still not ended
            GamePage gamePage=new GamePage();
            gamePage.serialize();
        }
        //closing the main stage and the quit window
        MainController.stage.close();
        MainPage.window.close();
    }

    /**
     * onKeyPressed function of YES button
     * @param event
     * @throws IOException
     */
    @FXML
    void clickYes1(KeyEvent event) throws IOException {

        //only if ENTER key is pressed
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

}
