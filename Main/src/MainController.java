import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainController {
    @FXML
    public void clickPlayGame() throws IOException {
        Stage stage=new Stage();
        NewGAme newGAme=new NewGAme();
        newGAme.start(stage);

    }
    @FXML
    public void clickSettings()
    {

    }
    @FXML
    public void clickQuit()
    {

    }
}
