import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    public void clickResume() throws IOException, ClassNotFoundException {
        GamePage gamePage=new GamePage();
        gamePage.deserialize();
    }
    @FXML
    public void clickBack() throws IOException
    {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/sample_main.fxml"));
        newgamepane.getChildren().setAll(pane);
    }
}
