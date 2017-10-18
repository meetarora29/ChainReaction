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
        AnchorPane pane= FXMLLoader.load(getClass().getResource("selectplayer.fxml"));
        newgamepane.getChildren().setAll(pane);
    }
    @FXML
    public void clickResume()
    {

    }
    @FXML
    public void clickBack() throws IOException
    {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("sample_main.fxml"));
        newgamepane.getChildren().setAll(pane);
    }
}
