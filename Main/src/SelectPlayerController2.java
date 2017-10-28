import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

public class SelectPlayerController2 {
    ObservableList<Integer> list= FXCollections.observableArrayList(2,3,4,5,6,7,8);
    ObservableList<Pair<Integer,Integer>> list1= FXCollections.observableArrayList(new Pair(8,10),new Pair(16,20),new Pair(24,30));
    int numberofPlayers;
    Pair<Integer,Integer> grid;

//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;

    @FXML
    private AnchorPane playerPane;

    @FXML
    private ChoiceBox<Integer> choiceBox;
    @FXML
    private ChoiceBox<Pair<Integer,Integer> > gridSize;

    @FXML
    void clickPlay(ActionEvent event) {
        getNumberOfPlayers(choiceBox);
        getGridSize(gridSize);
    }

    @FXML
    void initialize() {
        choiceBox.setItems(list);
        choiceBox.setValue(2);
//        numberofPlayers=choiceBox.getValue();


        gridSize.setItems(list1);
        gridSize.setValue(new Pair<>(8,10));
    }
    private void getNumberOfPlayers(ChoiceBox<Integer> choiceBox)
    {
        numberofPlayers=choiceBox.getValue();
        System.out.println(numberofPlayers);
    }
    private void getGridSize(ChoiceBox<Pair<Integer,Integer>> choiceBox)
    {
        grid=choiceBox.getValue();
        System.out.println(grid.getKey()+"x"+grid.getValue());
    }
}
