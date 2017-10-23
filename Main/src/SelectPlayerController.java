import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Observable;

public class SelectPlayerController {
    ObservableList<Integer> list= FXCollections.observableArrayList(2,3,4,5,6,7,8);
    ObservableList<Pair<Integer,Integer>> list1= FXCollections.observableArrayList(new Pair(8,10),new Pair(16,20),new Pair(24,30));
    int numberofPlayers;
    Pair<Integer,Integer> grid;
    @FXML
    private AnchorPane playerPane;

    @FXML
    private ChoiceBox<Integer> choiceBox;
    @FXML
    private ChoiceBox<Pair<Integer,Integer> > gridSize;

    @FXML
    private void initialize()
    {

        choiceBox.setItems(list);
        choiceBox.setValue(2);
//        numberofPlayers=choiceBox.getValue();


        gridSize.setItems(list1);
        gridSize.setValue(new Pair<>(8,10));
//        grid=gridSize.getValue();

    }
    @FXML
    void clickPlay(ActionEvent event) {
//        System.out.println(numberofPlayers);
//        System.out.println(grid.getKey()+"x"+grid.getValue());
//        GamePage game=new GamePage();
//        MainPage.window.close();
//        Stage stage=new Stage();
//        game.start(stage);
//        playerPane.getChildren().removeAll();
//        playerPane.getChildren().setAll(GamePage.grid);

    }
    @FXML
    void clickBack(ActionEvent event) throws IOException{
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/sample_newgame.fxml"));
        playerPane.getChildren().setAll(pane);
    }
    private void getNumberOfPlayers(ChoiceBox<Integer> choiceBox)
    {
        numberofPlayers=choiceBox.getValue();
        System.out.println(numberofPlayers);
    }
    private void getGridSize(ChoiceBox<Pair<Integer,Integer> > choiceBox)
    {
        grid=choiceBox.getValue();
        System.out.println(grid.getKey()+"x"+grid.getValue());
    }

}
