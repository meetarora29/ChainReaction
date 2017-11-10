import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
//import P;

public class SelectPlayerController2 {
    ObservableList<Integer> list= FXCollections.observableArrayList(2,3,4,5,6,7,8);
    ObservableList<P<Integer,Integer>> list1= FXCollections.observableArrayList(new P(9,6),new P<>(15,10));
    int numberofPlayers;
    P<Integer,Integer> grid;

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
    private ChoiceBox<P<Integer,Integer> > gridSize;

    @FXML
    void clickPlay(ActionEvent event) {
        GamePage.destroyGrid();
        GamePage gamePage=new GamePage();
        gamePage.setN(getGridSize().getKey());
        gamePage.setM(getGridSize().getValue());
        gamePage.setNumPlayers(getNumberOfPlayers());
        MainPage.window.close();

        gamePage.start(MainPage.window);
    }

    @FXML
    void initialize() {
        choiceBox.setItems(list);
        choiceBox.setValue(2);
//        numberofPlayers=choiceBox.getValue();


        gridSize.setItems(list1);
        gridSize.setValue(new P<>(9,6));
    }
    public int getNumberOfPlayers()
    {
        numberofPlayers=choiceBox.getValue();
        System.out.println(numberofPlayers);
        return numberofPlayers;
    }
    public P<Integer,Integer> getGridSize()
    {
        grid=gridSize.getValue();
        System.out.println(grid.getKey()+"x"+grid.getValue());
        return grid;
    }
}
