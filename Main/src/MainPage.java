import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.naming.Binding;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * This is the Main Page class of the game from where the game starts
 *It cotains the main functions and the main window on which our game will be working
 * @author Gagandeep Singh-2016037
 * @since 14-10-2017
 */
public class MainPage extends Application {

    //Our main window on which our game works
    public static Stage window;

    /**
     * This is the main function through which
     * our game starts.
     * Whenever our game starts it calls function initColours()
     * which initialises the Color array.
     * @param args Any arguments as input
     */
    public static void main(String[] args)
    {
        initColours();
        launch(args);
    }

    //Color array. It contains the color for all the 8 player throughout the game
    public static Color[] colours=new Color[8];

    /**
     * This is the getter function of the colours array
     * @return the colours array
     */
    public static Color[] getColours() {
        return colours;
    }


    /**
     * This function initialises the colours array with certain
     * Color values whenever the game is launched.
     */
    public static void initColours()
    {
        MainPage.colours[0]=Color.web("#42f4d7");
        MainPage.colours[1]=Color.web("#FA8072");
        MainPage.colours[2]=Color.web("#f44168");
        MainPage.colours[3]=Color.web("#1a3399");
        MainPage.colours[4]=Color.web("#b541f4");
        MainPage.colours[5]=Color.web("#994d66");
        MainPage.colours[6]=Color.web("#f5f900");
        MainPage.colours[7]=Color.web("#000");
    }


    /**
     * This function is used to start the game
     * Overriden because this class extends Application class
     * @param primaryStage The stage on which the game starts
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        window=primaryStage;
        window.setTitle("Chain Reaction");//set the title as "CHAIN_REACTION"
        window.setResizable(false);//the window is not resizable, it would remzain of the same size.

        //whenever we press the cross button
        window.setOnCloseRequest(e -> {
            try {
                e.consume();
                closeprogram();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        //loading the fxml file from scene builder into an AnchorPane
        AnchorPane pane=FXMLLoader.load(getClass().getResource("fxml_files/sample_main.fxml"));

        //Setting the background image
        String image= GamePage.class.getResource("images/image.jpg").toExternalForm();
        pane.setStyle("-fx-background-image: url('"+ image +"')");

        Scene scene1=new Scene(pane);

        //assigning stylesheet to the scene
        scene1.getStylesheets().add("css/MainPage.css");

        //settnin scene on window
        window.setScene(scene1);

        // Display the stage
        window.show();
    }

    /**
     * function that controls what to do whenever there
     * is a request to close the game
     * @throws IOException
     */
    private void closeprogram() throws IOException {
        MainController.stage=new Stage();
        MainController.stage.setTitle("Quit");
        MainController.stage.initModality(Modality.APPLICATION_MODAL);
        AnchorPane pane=FXMLLoader.load(getClass().getResource("fxml_files/quitGame.fxml"));
        MainController.stage.setScene(new Scene(pane));
        MainController.stage.show();
    }
}
