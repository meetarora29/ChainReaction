import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;

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

}
