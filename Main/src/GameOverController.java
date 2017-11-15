
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * This class gives you three options when
 * the game gets over
 *          1. New Game: Helps user to again select number of players and
 *                       grid size and start a new game
 *          2. Restart: Restarts the game just played
 *          3. Quit: Quits the game
 * @author Gagandeep Singh-2016037
 */
public class GameOverController {


    public static Stage stage1;

    /**
     * onAction function for New Game button
     * @throws IOException
     */
    @FXML
    void clickNewGame() throws IOException{
        GamePage.destroyGrid();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/selectplayer.fxml"));

        String image= GamePage.class.getResource("images/image.jpg").toExternalForm();
        pane.setStyle("-fx-background-image: url('"+ image +"')");
        Grid.stage.close();
        Scene scene=new Scene(pane);
        if(GamePage.getGrid().win_player!=null)
            GamePage.getGrid().win_player.stop();
        MainPage.window.setScene(scene);
        MainPage.window.setTitle("Chain Reaction");

    }

    /**
     * onKeyPressed function for New Game button
     * @param event This event is a key event which checks whether the key
     *              pressed is ENTER or not
     * @throws IOException
     */
    @FXML
    void clickNewGame1(KeyEvent event) throws IOException{
        if(event.getCode()== KeyCode.ENTER) {
            GamePage.destroyGrid();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml_files/selectplayer.fxml"));

            String image = GamePage.class.getResource("images/image.jpg").toExternalForm();
            pane.setStyle("-fx-background-image: url('" + image + "')");
            Grid.stage.close();
            Scene scene = new Scene(pane);
            if(GamePage.getGrid().win_player!=null)
                GamePage.getGrid().win_player.stop();
            MainPage.window.setScene(scene);
            MainPage.window.setTitle("Chain Reaction");
        }

    }

    /**
     * onAction function for Ouit button
     * @throws IOException
     */
    @FXML
    void clickQuit() throws IOException {
        stage1 = new Stage();
        stage1.setResizable(false);
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.setTitle("Quit");
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/quit2.fxml"));
        stage1.setScene(new Scene(pane));
        stage1.show();
    }

    /**
     * onKeyPressed function for Ouit button
     * @param event This event is a key event which checks whether the key
     *              pressed is ENTER or not
     * @throws IOException
     */
    @FXML
    void clickQuit1(KeyEvent event) throws IOException {
        if(event.getCode()==KeyCode.ENTER) {
            stage1 = new Stage();
            stage1.setResizable(false);
            stage1.initModality(Modality.APPLICATION_MODAL);
            stage1.setTitle("Quit");
            AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml_files/quit2.fxml"));
            stage1.setScene(new Scene(pane));
            stage1.show();
        }
    }

    /**
     * onAction function for Restart button
     */
    @FXML
    void clickRestart() {
        Grid.stage.close();
        Grid grid=GamePage.getGrid();
        if(grid.win_player!=null)
            grid.win_player.stop();
        grid.restartGame();
    }

    /**
     * onKeyPR\ressed function for Restart button
     * @param event This event is a key event which checks whether the key
     *              pressed is ENTER or not
     */
    @FXML
    void clickRestart1(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER) {
            Grid.stage.close();
            Grid grid=GamePage.getGrid();
            if(grid.win_player!=null)
                grid.win_player.stop();
            grid.restartGame();
        }

    }


}
