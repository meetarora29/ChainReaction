import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Observable;

public class SelectPlayerController {
    ObservableList<Integer> list= FXCollections.observableArrayList(2,3,4,5,6,7,8);
    @FXML
    private AnchorPane playerPane;

    @FXML
    private ComboBox<Integer> comboBox;

    public ComboBox<Integer> getComboBox() {
        return comboBox;
    }

    public void setComboBox(ComboBox<Integer> comboBox) {
        this.comboBox = comboBox;

    }
    @FXML
    private void initialize()
    {
        comboBox.setValue(2);
        comboBox.setItems(list);

    }
    @FXML
    void clickPlay(ActionEvent event) {

    }
    @FXML
    void clickBack(ActionEvent event) throws IOException{
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/sample_newgame.fxml"));
        playerPane.getChildren().setAll(pane);
    }


}
