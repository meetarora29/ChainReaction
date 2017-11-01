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
class P<T1,T2>
{
    private T1 key;
    private T2 value;

    public P(T1 key, T2 value) {
        this.key = key;
        this.value = value;
    }

    public T1 getKey() {
        return key;
    }

    public void setKey(T1 key) {
        this.key = key;
    }

    public T2 getValue() {
        return value;
    }

    public void setValue(T2 value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return key+"x"+value;
    }
}
public class SelectPlayerController {
    ObservableList<Integer> list= FXCollections.observableArrayList(2,3,4,5,6,7,8);
    ObservableList<P<Integer,Integer>> list1= FXCollections.observableArrayList(new P(9,6),new P(15,10));
    Stage primaryStage=new Stage();
    int numberofPlayers;
    P<Integer,Integer> grid;
//    int n=9, m=6;
//    int numPlayers=2;

//    public int getN() {
//        return n;
//    }
//
//    public void setN(int n) {
//        this.n = n;
//    }
//
//    public int getM() {
//        return m;
//    }
//
//    public void setM(int m) {
//        this.m = m;
//    }
//
//    public int getNumPlayers() {
//        return numPlayers;
//    }
//
//    public void setNumPlayers(int numPlayers) {
//        this.numPlayers = numPlayers;
//    }

    @FXML
    private AnchorPane playerPane;

    @FXML
    private ChoiceBox<Integer> choiceBox;
    @FXML
    private ChoiceBox<P<Integer,Integer> > gridSize;

    @FXML
    private void initialize()
    {

        choiceBox.setItems(list);
        choiceBox.setValue(2);
//        numberofPlayers=choiceBox.getValue();


        gridSize.setItems(list1);
        gridSize.setValue(new P<>(9,6));
        
//        grid=gridSize.getValue();

    }
    @FXML
    void clickPlay(ActionEvent event) {
            GamePage gamePage=new GamePage();
            gamePage.setN(getGridSize().getKey());
            gamePage.setM(getGridSize().getValue());
            gamePage.setNumPlayers(getNumberOfPlayers());
            MainPage.window.close();

            gamePage.start(MainPage.window);
//        System.out.println(numberofPlayers);
//        System.out.println(grid.getKey()+"x"+grid.getValue());
//        GamePage game=new GamePage();

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
