import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;

/**
 * This class is the controller class for exiting the game
 * It asks the user to confirm the quit
 * if they press the YES button
 * This class is active when the game gets over and user decides to exit
 * @author Gagandeep Singh-2016037
 */
public class quit2Controller {


    /**
     * onAction function of the no button
     * it closes the quit window
     * @param event
     */
    @FXML
    void clickNo(ActionEvent event) {
        GameOverController.stage1.close();

    }

    /**
     * onKeyPressed function of the no button
     * it closes the quit window
     * @param event
     */
    @FXML
    void clickNo1(javafx.scene.input.KeyEvent event) {
        if(event.getCode()== KeyCode.ENTER) {
            GameOverController.stage1.close();
        }
    }

    /**
     * onAction function of the Yes button
     * it quits the game and close all the windows
     * @param event
     */
    @FXML
    void clickYes(ActionEvent event) {
        MainPage.window.close();
        Grid.stage.close();
        GameOverController.stage1.close();
    }

    /**
     * onKeyPressed function of the Yes button
     * it quits the game and close all the windows
     * @param event
     */
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
