import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 *This is the controller class for the New Game page
 * It consists of 3 buttons
 *          1. New Game: Starts a new game
 *          2. Resume: Resumes the last ongoing game
 *          3. Back: Takes us to the previous page
 */
public class NewGameController {
    @FXML
    private AnchorPane newgamepane;

    /**
     * onAction function for new Game button
     * @throws IOException
     */
    @FXML
    public void clickNewGame() throws IOException
    {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/selectplayer.fxml"));
        newgamepane.getChildren().setAll(pane);
    }

    /**
     * onKeyPressed function of New Game button
     * @param event This event is a key event which checks whether the key
     *              pressed is ENTER or not
     * @throws IOException
     */
    @FXML
    public void clickNewGame1(KeyEvent event) throws IOException
    {
        if(event.getCode()== KeyCode.ENTER) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml_files/selectplayer.fxml"));
            newgamepane.getChildren().setAll(pane);
        }
    }

    /**
     * onAction function for Resume button
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    public void clickResume() throws IOException, ClassNotFoundException {
        GamePage gamePage=new GamePage();
        gamePage.deserialize();
    }

    /**
     * onKeyPressed function for Resume button
     * @param event This event is a key event which checks whether the key
     *              pressed is ENTER or not
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    public void clickResume1(KeyEvent event) throws IOException, ClassNotFoundException {
        if(event.getCode()==KeyCode.ENTER) {
            GamePage gamePage = new GamePage();
            gamePage.deserialize();
        }
    }

    /**
     * onAction function for Back button
     * @throws IOException
     */
    @FXML
    public void clickBack() throws IOException
    {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/sample_main.fxml"));
        newgamepane.getChildren().setAll(pane);
    }

    /**
     * onKeyPressed function for Back button
     * @param event This event is a key event which checks whether the key
     *              pressed is ENTER or not
     * @throws IOException
     */
    @FXML
    public void clickBack1(KeyEvent event) throws IOException
    {
        if(event.getCode()==KeyCode.ENTER) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml_files/sample_main.fxml"));
            newgamepane.getChildren().setAll(pane);
        }
    }
}
