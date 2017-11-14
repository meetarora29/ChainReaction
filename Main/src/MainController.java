import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import javafx.scene.input.KeyEvent;

import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class is the controller class for the main page.
 * It implements the action handler(Mouseclick,KeyEvents)
 * of the buttons on the MainPage
 * @author Gagandeep Singh-2016037
 */
public class MainController {

    //Stage for confirming exit from the game
    public static Stage stage;

    //Color array for storing the colors of the main Color array
    Color[] colours=new Color[8];

    /*Pane on which the the following
        game panes are shown
     */
    @FXML
    private AnchorPane rootPane;


    /**
     * onAction function for  Play Game Button
     * @param event
     * @throws IOException
     */
    @FXML
    void clickPlayGame(ActionEvent event) throws IOException {
        colours=MainPage.getColours();
        Path path= Paths.get("game.dat");
        AnchorPane pane;
        if(Files.exists(path)) {
             pane = FXMLLoader.load(getClass().getResource("fxml_files/sample_newgame.fxml"));
        }
        else
        {
             pane = FXMLLoader.load(getClass().getResource("fxml_files/newGame2.fxml"));

        }
        rootPane.getChildren().setAll(pane);
    }

    /**
     * onKeyPressed function of Play Game button
     * @param event
     * @throws IOException
     */
    @FXML
    void clickPlayGame1(KeyEvent event) throws IOException {
        //only if ENTER key is pressed
        if(event.getCode()== KeyCode.ENTER) {
            //initialising color array
            colours = MainPage.getColours();

            //creating a path which consists a file that contains of the state of the game
            Path path = Paths.get("game.dat");
            AnchorPane pane;
            //checking if the file to the given path exists
            if (Files.exists(path)) {
                /*
                if file exists, go to a "sample_newgame.fxml"
                 */
                pane = FXMLLoader.load(getClass().getResource("fxml_files/sample_newgame.fxml"));
            }
            /*
            else go to a "newGame2.fxml"
             */
            else {
                pane = FXMLLoader.load(getClass().getResource("fxml_files/newGame2.fxml"));

            }
            rootPane.getChildren().setAll(pane);
        }
    }

    /**
     * onAction function for  Quit Button
     * @param event
     * @throws IOException
     */
    @FXML
    void clickQuit(ActionEvent event) throws IOException {
        //make a new stage
        stage=new Stage();

            /*the windows behind the stage
                could not be used until
                we deal with this stage
             */
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setTitle("Quit");

        //load the next fxml file
        AnchorPane pane=FXMLLoader.load(getClass().getResource("fxml_files/quitGame.fxml"));

        //Setting the scene on the stage
        stage.setScene(new Scene(pane));

        //Display the stage
        stage.show();
    }

    /**
     * onKeyPressed function for  Quit Button
     * @param event
     * @throws IOException
     */
    @FXML
    void clickQuit1(KeyEvent event) throws IOException {
        if(event.getCode()== KeyCode.ENTER) {
            //make a new stage
            stage=new Stage();

            /*the windows behind the stage
                could not be used until
                we deal with this stage
             */
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Quit");

            //load the next fxml file
            AnchorPane pane=FXMLLoader.load(getClass().getResource("fxml_files/quitGame.fxml"));

            //Setting the scene on the stage
            stage.setScene(new Scene(pane));

            //Display the stage
            stage.show();
        }
    }

    /**
     * onAction function for Settings button
     * @param event
     * @throws IOException
     */
    @FXML
    void clickSettings(ActionEvent event) throws IOException{
        //load the next fxml file
        AnchorPane pane=FXMLLoader.load(getClass().getResource("fxml_files/settings.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    /**
     * onKeyPressed function for Settings button
     * @param event
     * @throws IOException
     */
    @FXML
    void clickSettings1(KeyEvent event) throws IOException {
        //only when ENTER key is pressed
        if(event.getCode()== KeyCode.ENTER) {
            //load the next fxml file
            AnchorPane pane=FXMLLoader.load(getClass().getResource("fxml_files/settings.fxml"));
            rootPane.getChildren().setAll(pane);
        }
    }

//    @FXML
//    public void initialize() {
//
//    }

}
