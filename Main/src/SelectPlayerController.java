import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
//import P;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Observable;

/**
 * This class is just like Pair class
 * The only difference is in the toString function
 * Which was needed to display in the choice-box used further
 * @param <T1>
 * @param <T2>
 * @author Gagandeep Singh-2016037
 */
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

/**
 * This is the controller class for Selecting number of players
 * and the grid size in which they want to play
 * Buttons: 1. Play: Starts the game
 *          2. Back: Takes you to the previous pane
 * @author Gagandeep Singh-2016037
 */
public class SelectPlayerController {
    ObservableList<Integer> list= FXCollections.observableArrayList(1,2,3,4,5,6,7,8);
    P<Integer,Integer> p1=new P(9,6);
    P<Integer,Integer> p2=new P(15,10);
    ObservableList<P<Integer,Integer>> list1= FXCollections.observableArrayList(p1,p2);
    int numberofPlayers;
    P<Integer,Integer> grid;



    @FXML
    private AnchorPane playerPane;

    /*
    Choice box for selecting number of players
     */
    @FXML
    private ChoiceBox<Integer> choiceBox;

    /*
    Choice box for selecting the grid size
     */
    @FXML
    private ChoiceBox<P<Integer,Integer> > gridSize;


    /**
     * function to initialize values in the choice boxes
     * and the default values
     */
    @FXML
    private void initialize()
    {

        choiceBox.setItems(list);
        choiceBox.setValue(2);
        choiceBox.addEventHandler(KeyEvent.KEY_PRESSED,event -> {
            if (event.getCode()==KeyCode.ENTER)
            {
                choiceBox.show();
            }
        });
        choiceBox.setTooltip(new Tooltip("Select number of players"));

        gridSize.setItems(list1);
        gridSize.setValue(p1);
        gridSize.setTooltip(new Tooltip("Select grid size"));
    }

    /**
     * onAction function for play button
     * @param event
     */
    @FXML
    void clickPlay(ActionEvent event) {
            GamePage gamePage=new GamePage();
            gamePage.setN(getGridSize().getKey());
            gamePage.setM(getGridSize().getValue());
            gamePage.setNumPlayers(getNumberOfPlayers());
            MainPage.window.close();

            gamePage.start(MainPage.window);


    }

    /**
     * onKeyPressed function for play button
     * @param event
     */
    @FXML
    void clickPlay1(KeyEvent event) {
        if(event.getCode()== KeyCode.ENTER) {
            GamePage gamePage = new GamePage();
            gamePage.setN(getGridSize().getKey());
            gamePage.setM(getGridSize().getValue());
            gamePage.setNumPlayers(getNumberOfPlayers());
            MainPage.window.close();
            gamePage.start(MainPage.window);
        }
    }

    /**
     * onAction function for the back button
     * @param event
     * @throws IOException
     */
    @FXML
    void clickBack(ActionEvent event) throws IOException{
        Path path= Paths.get("game.dat");
        AnchorPane pane;
        if(Files.exists(path)) {
            pane = FXMLLoader.load(getClass().getResource("fxml_files/sample_newgame.fxml"));
        }
        else
        {
            pane = FXMLLoader.load(getClass().getResource("fxml_files/newGame2.fxml"));

        }
        playerPane.getChildren().setAll(pane);
    }

    /**
     * onKeyPressed function for the back button
     * @param event
     * @throws IOException
     */
    @FXML
    void clickBack1(KeyEvent event) throws IOException{
        if(event.getCode()== KeyCode.ENTER) {
            Path path = Paths.get("game.dat");
            AnchorPane pane;
            if (Files.exists(path)) {
                pane = FXMLLoader.load(getClass().getResource("fxml_files/sample_newgame.fxml"));
            } else {
                pane = FXMLLoader.load(getClass().getResource("fxml_files/newGame2.fxml"));

            }
            playerPane.getChildren().setAll(pane);
        }
    }

    /**
     * function that returns the number of players selected by the user
     * @return
     */
    public int getNumberOfPlayers()
    {
        numberofPlayers=choiceBox.getValue();
        System.out.println(numberofPlayers);
        return numberofPlayers;
    }

    /**
     * function that returns the grid size selected by the user
     * @return
     */
    public P<Integer,Integer> getGridSize()
    {
        grid=gridSize.getValue();
        System.out.println(grid.getKey()+"x"+grid.getValue());
        return grid;
    }

}
