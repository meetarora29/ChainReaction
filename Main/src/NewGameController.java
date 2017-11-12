import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class NewGameController {
    @FXML
    private AnchorPane newgamepane;
    @FXML
    public void clickNewGame() throws IOException
    {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/selectplayer.fxml"));
        newgamepane.getChildren().setAll(pane);
    }
    @FXML
    public void clickNewGame1(KeyEvent event) throws IOException
    {
        if(event.getCode()== KeyCode.ENTER) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml_files/selectplayer.fxml"));
            newgamepane.getChildren().setAll(pane);
        }
    }
    @FXML
    public void clickResume() throws IOException, ClassNotFoundException {
        GamePage gamePage=new GamePage();
        gamePage.deserialize();
    }
    @FXML
    public void clickResume1(KeyEvent event) throws IOException, ClassNotFoundException {
        if(event.getCode()==KeyCode.ENTER) {
            GamePage gamePage = new GamePage();
            gamePage.deserialize();
        }
    }
    @FXML
    public void clickBack() throws IOException
    {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/sample_main.fxml"));
        newgamepane.getChildren().setAll(pane);
    }
    @FXML
    public void clickBack1(KeyEvent event) throws IOException
    {
        if(event.getCode()==KeyCode.ENTER) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml_files/sample_main.fxml"));
            newgamepane.getChildren().setAll(pane);
        }
    }
}
