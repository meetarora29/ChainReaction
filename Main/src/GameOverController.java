/**
 * Sample Skeleton for 'game_end.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class GameOverController{

//    @FXML // ResourceBundle that was given to the FXMLLoader
//    private ResourceBundle resources;
//
//    @FXML // URL location of the FXML file that was given to the FXMLLoader
//    private URL location;
    public static Stage stage1;
    @FXML
    private AnchorPane gamePane;
    @FXML
    void clickNewGame(ActionEvent event) throws IOException{
        GamePage.destroyGrid();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/selectplayer2.fxml"));
//        gamePane.getChildren().setAll(pane);
//        MainPage.window.close();
        Grid.stage.close();
//        Stage stage=new Stage();
        Scene scene=new Scene(pane);
        MainPage.window.setScene(scene);
        MainPage.window.setTitle("Chain Reaction");
//        MainPage.window.show();
    }

    @FXML
    void clickQuit(ActionEvent event) throws IOException {
        stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.setTitle("Quit");
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/quit2.fxml"));
        stage1.setScene(new Scene(pane));
        stage1.show();
    }

    @FXML
    void clickRestart(ActionEvent event) {
        GamePage gamePage=new GamePage();
        MainPage.window.close();
        Grid.stage.close();
        gamePage.start(MainPage.window);

    }



}
