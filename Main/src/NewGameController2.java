import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 *This is the controller class for the New Game page
 * It consists of 3 buttons
 *          1. New Game: Starts a new game
 *          2. Back: Takes us to the previous page
 * @author Gagandeep Singh-2016037
 */
public class NewGameController2 {

    @FXML
    private AnchorPane newgamepane;

    /**
     * onAction function for Back button
     */
    @FXML
    void clickBack() {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(getClass().getResource("fxml_files/sample_main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        newgamepane.getChildren().setAll(pane);
    }

    /**
     * onKeyPressed function for Back button
     * @param event This event is a key event which checks whether the key
     *              pressed is ENTER or not
     */
    @FXML
    void clickBack1(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER) {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("fxml_files/sample_main.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            newgamepane.getChildren().setAll(pane);
        }
    }

    /**
     *  onAction function for new Game button
     */
    @FXML
    void clickNewGame() {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(getClass().getResource("fxml_files/selectplayer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        newgamepane.getChildren().setAll(pane);
    }

    /**
     * onKeyPressed function for new Game button
     * @param event This event is a key event which checks whether the key
     *              pressed is ENTER or not
     */
    @FXML
    void clickNewGame1(KeyEvent event) {
        if(event.getCode()== KeyCode.ENTER) {
            AnchorPane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("fxml_files/selectplayer.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            newgamepane.getChildren().setAll(pane);
        }
    }

    /*
    initializing function for the pane
    (auto-generated)
     */
    @FXML
    void initialize() {
        assert newgamepane != null : "fx:id=\"newgamepane\" was not injected: check your FXML file 'newGame2.fxml'.";

    }
}
