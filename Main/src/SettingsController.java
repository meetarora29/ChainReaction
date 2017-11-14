import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.awt.Color.*;

/**
 * This class is the controller class for Settings page
 * This page consists of 8 Color Pickers corresponding to
 * the 8 players
 * @author Gagandeep Singh-2016037
 */
public class SettingsController {

    //Pane on which the following scenes of the game are shown
    @FXML
    private AnchorPane settingsPane;

    //Color Picker1
    @FXML
    private ColorPicker color1;

    //Color Picker2
    @FXML
    private ColorPicker color2;

    //Color Picker3
    @FXML
    private ColorPicker color3;

    //Color Picker4
    @FXML
    private ColorPicker color4;

    //Color Picker5
    @FXML
    private ColorPicker color5;

    //Color Picker6
    @FXML
    private ColorPicker color6;

    //Color Picker7
    @FXML
    private ColorPicker color7;

    //Color Picker8
    @FXML
    private ColorPicker color8;

    /**
     * onAction function for the done button
     * it confirms the colours selected on the 8 colour pickers
     * @param event
     * @throws IOException
     */
    @FXML
    void clickDone(ActionEvent event) throws IOException {



        color1.setValue(MainPage.colours[0]);
        color2.setValue(MainPage.colours[1]);
        color3.setValue(MainPage.colours[2]);
        color4.setValue(MainPage.colours[3]);
        color5.setValue(MainPage.colours[4]);
        color6.setValue(MainPage.colours[5]);
        color7.setValue(MainPage.colours[6]);
        color8.setValue(MainPage.colours[7]);
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/sample_main.fxml"));
        settingsPane.getChildren().setAll(pane);

    }

    /**
     * onKeyPressed function for the done button
     * it confirms the colours selected on the 8 colour pickers
     * @param event
     * @throws IOException
     */
    @FXML
    void clickDone1(KeyEvent event) throws IOException {


        if(event.getCode()== KeyCode.ENTER) {
            color1.setValue(MainPage.colours[0]);
            color2.setValue(MainPage.colours[1]);
            color3.setValue(MainPage.colours[2]);
            color4.setValue(MainPage.colours[3]);
            color5.setValue(MainPage.colours[4]);
            color6.setValue(MainPage.colours[5]);
            color7.setValue(MainPage.colours[6]);
            color8.setValue(MainPage.colours[7]);
            AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml_files/sample_main.fxml"));
            settingsPane.getChildren().setAll(pane);
        }

    }

    /**
     * This function takes the value of 1st color picker
     * initialises the 0th index of the main colour array
     * If the colour selected is white it shows an error
     * If the colour selected is already selected by another player then also it shows an error
     * The error is shown in a pop up window which can be exited by pressing ESCAPE button
     * It resets the value to the original on after exiting the error window
     * @param event
     * @throws IOException
     */
    @FXML
    void selectColor1(ActionEvent event) throws IOException {
        Color c=color1.getValue();

        MainPage.colours[0]=c;
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("Error");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOnCloseRequest(event1 -> {
                color1.setValue(Color.web("#42f4d7"));
                Color c1=color1.getValue();
                MainPage.colours[0]=c1;
            });
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color1.setValue(Color.web("#42f4d7"));
                    Color c1=color1.getValue();
                    MainPage.colours[0]=c1;
                    stage.close();
                }
            });
        }
        if(c.equals(MainPage.colours[1]) ||c.equals(MainPage.colours[2]) || c.equals(MainPage.colours[3])||c.equals(MainPage.colours[4])||c.equals(MainPage.colours[5]) ||c.equals(MainPage.colours[6]) ||c.equals(MainPage.colours[7])  )
        {
            AnchorPane pane;
                Stage stage=new Stage();
                stage.setResizable(false);
                stage.setTitle("Error");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setOnCloseRequest(event1 -> {
                    color1.setValue(Color.web("#42f4d7"));
                    Color c1=color1.getValue();
                    MainPage.colours[0]=c1;
                });
                pane=FXMLLoader.load(getClass().getResource("fxml_files/error.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color1.setValue(Color.web("#42f4d7"));
                    Color c1=color1.getValue();
                    MainPage.colours[0]=c1;
                    stage.close();
                }
            });
        }


    }

    /**
     * This function takes the value of 2nd color picker
     * initialises the 1st index of the main colour array
     * If the colour selected is white it shows an error
     * If the colour selected is already selected by another player then also it shows an error
     * The error is shown in a pop up window which can be exited by pressing ESCAPE button
     * It resets the value to the original on after exiting the error window
     * @param event
     * @throws IOException
     */
    @FXML
    void selectColor2(ActionEvent event) throws IOException {
        Color c=color2.getValue();
        MainPage.colours[1]=c;
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color2.setValue(Color.web("#FA8072"));
                Color c1=color2.getValue();
                MainPage.colours[1]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color2.setValue(Color.web("#FA8072"));
                    Color c1=color2.getValue();
                    MainPage.colours[1]=c1;
                    stage.close();
                }
            });
        }
        if(c.equals(MainPage.colours[0]) ||c.equals(MainPage.colours[2]) || c.equals(MainPage.colours[3])||c.equals(MainPage.colours[4])||c.equals(MainPage.colours[5]) ||c.equals(MainPage.colours[6]) ||c.equals(MainPage.colours[7])  )
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color2.setValue(Color.web("#FA8072"));
                Color c1=color2.getValue();
                MainPage.colours[1]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color2.setValue(Color.web("#FA8072"));
                    Color c1=color2.getValue();
                    MainPage.colours[1]=c1;
                    stage.close();
                }
            });
        }

    }

    /**
     * This function takes the value of 3rd color picker
     * initialises the 2nd index of the main colour array
     * If the colour selected is white it shows an error
     * If the colour selected is already selected by another player then also it shows an error
     * The error is shown in a pop up window which can be exited by pressing ESCAPE button
     * It resets the value to the original on after exiting the error window
     * @param event
     * @throws IOException
     */
    @FXML
    void selectColor3(ActionEvent event) throws IOException {
        Color c=color3.getValue();
        MainPage.colours[2]=c;
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color3.setValue(Color.web("#f44168"));
                Color c1=color3.getValue();
                MainPage.colours[2]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color3.setValue(Color.web("#f44168"));
                    Color c1=color3.getValue();
                    MainPage.colours[2]=c1;
                    stage.close();
                }
            });
        }
        if(c.equals(MainPage.colours[1]) ||c.equals(MainPage.colours[0]) || c.equals(MainPage.colours[3])||c.equals(MainPage.colours[4])||c.equals(MainPage.colours[5]) ||c.equals(MainPage.colours[6]) ||c.equals(MainPage.colours[7])  )
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color3.setValue(Color.web("#f44168"));
                Color c1=color3.getValue();
                MainPage.colours[2]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color3.setValue(Color.web("#f44168"));
                    Color c1=color3.getValue();
                    MainPage.colours[2]=c1;
                    stage.close();
                }
            });
        }

    }

    /**
     * This function takes the value of 4th color picker
     * initialises the 3rd index of the main colour array
     * If the colour selected is white it shows an error
     * If the colour selected is already selected by another player then also it shows an error
     * The error is shown in a pop up window which can be exited by pressing ESCAPE button
     * It resets the value to the original on after exiting the error window
     * @param event
     * @throws IOException
     */
    @FXML
    void selectColor4(ActionEvent event) throws IOException {
        Color c=color4.getValue();
        MainPage.colours[3]=c;
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color4.setValue(Color.web("#1a3399"));
                Color c1=color4.getValue();
                MainPage.colours[3]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color4.setValue(Color.web("#1a3399"));
                    Color c1=color4.getValue();
                    MainPage.colours[3]=c1;
                    stage.close();
                }
            });
        }
        if(c.equals(MainPage.colours[1]) ||c.equals(MainPage.colours[2]) || c.equals(MainPage.colours[0])||c.equals(MainPage.colours[4])||c.equals(MainPage.colours[5]) ||c.equals(MainPage.colours[6]) ||c.equals(MainPage.colours[7])  )
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color4.setValue(Color.web("#c7f441"));
                Color c1=color4.getValue();
                MainPage.colours[3]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color4.setValue(Color.web("#1a3399"));
                    Color c1=color4.getValue();
                    MainPage.colours[3]=c1;
                    stage.close();
                }
            });
        }

    }


    /**
     * This function takes the value of 5th color picker
     * initialises the 4th index of the main colour array
     * If the colour selected is white it shows an error
     * If the colour selected is already selected by another player then also it shows an error
     * The error is shown in a pop up window which can be exited by pressing ESCAPE button
     * It resets the value to the original on after exiting the error window
     * @param event
     * @throws IOException
     */
    @FXML
    void selectColor5(ActionEvent event) throws IOException {
        Color c=color5.getValue();
        MainPage.colours[4]=c;
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color5.setValue(Color.web("#b541f4"));
                Color c1=color5.getValue();
                MainPage.colours[4]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color5.setValue(Color.web("#b541f4"));
                    Color c1=color5.getValue();
                    MainPage.colours[4]=c1;
                    stage.close();
                }
            });
        }
        if(c.equals(MainPage.colours[1]) ||c.equals(MainPage.colours[2]) || c.equals(MainPage.colours[3])||c.equals(MainPage.colours[0])||c.equals(MainPage.colours[5]) ||c.equals(MainPage.colours[6]) ||c.equals(MainPage.colours[7])  )
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color5.setValue(Color.web("#b541f4"));
                Color c1=color5.getValue();
                MainPage.colours[4]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color5.setValue(Color.web("#b541f4"));
                    Color c1=color5.getValue();
                    MainPage.colours[4]=c1;
                    stage.close();
                }
            });
        }

    }

    /**
     * This function takes the value of 6th color picker
     * initialises the 5th index of the main colour array
     * If the colour selected is white it shows an error
     * If the colour selected is already selected by another player then also it shows an error
     * The error is shown in a pop up window which can be exited by pressing ESCAPE button
     * It resets the value to the original on after exiting the error window
     * @param event
     * @throws IOException
     */
    @FXML
    void selectColor6(ActionEvent event) throws IOException {
        Color c=color6.getValue();
        MainPage.colours[5]=c;
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color6.setValue(Color.web("#994d66"));
                Color c1=color6.getValue();
                MainPage.colours[5]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color6.setValue(Color.web("#994d66"));
                    Color c1=color6.getValue();
                    MainPage.colours[5]=c1;
                    stage.close();
                }
            });
        }
        if(c.equals(MainPage.colours[1]) ||c.equals(MainPage.colours[2]) || c.equals(MainPage.colours[3])||c.equals(MainPage.colours[4])||c.equals(MainPage.colours[0]) ||c.equals(MainPage.colours[6]) ||c.equals(MainPage.colours[7])  )
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color6.setValue(Color.web("#994d66"));
                Color c1=color6.getValue();
                MainPage.colours[5]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color6.setValue(Color.web("#994d66"));
                    Color c1=color6.getValue();
                    MainPage.colours[5]=c1;
                    stage.close();
                }
            });
        }

    }

    /**
     * This function takes the value of 7th color picker
     * initialises the 6th index of the main colour array
     * If the colour selected is white it shows an error
     * If the colour selected is already selected by another player then also it shows an error
     * The error is shown in a pop up window which can be exited by pressing ESCAPE button
     * It resets the value to the original on after exiting the error window
     * @param event
     * @throws IOException
     */
    @FXML
    void selectColor7(ActionEvent event) throws IOException {
        Color c=color7.getValue();
        MainPage.colours[6]=c;
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color7.setValue(Color.web("#f5f900"));
                Color c1=color7.getValue();
                MainPage.colours[6]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color7.setValue(Color.web("#f5f900"));
                    Color c1=color7.getValue();
                    MainPage.colours[6]=c1;
                    stage.close();
                }
            });
        }
        if(c.equals(MainPage.colours[1]) ||c.equals(MainPage.colours[2]) || c.equals(MainPage.colours[3])||c.equals(MainPage.colours[4])||c.equals(MainPage.colours[5]) ||c.equals(MainPage.colours[0]) ||c.equals(MainPage.colours[7])  )
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color7.setValue(Color.web("#f5f900"));
                Color c1=color7.getValue();
                MainPage.colours[6]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color7.setValue(Color.web("#f5f900"));
                    Color c1=color7.getValue();
                    MainPage.colours[6]=c1;
                    stage.close();
                }
            });
        }

    }

    /**
     * This function takes the value of 8th color picker
     * initialises the 7th index of the main colour array
     * If the colour selected is white it shows an error
     * If the colour selected is already selected by another player then also it shows an error
     * The error is shown in a pop up window which can be exited by pressing ESCAPE button
     * It resets the value to the original on after exiting the error window
     * @param event
     * @throws IOException
     */
    @FXML
    void selectColor8(ActionEvent event) throws IOException {
        Color c=color8.getValue();
        MainPage.colours[7]=c;
        /*
        if colour selected is white
        show an error window and on exiting th error window
        reset the colour with the initial value
         */
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color8.setValue(Color.web("#000"));
                Color c1=color8.getValue();
                MainPage.colours[7]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            /*
            Exit error window on pressing ESCAPE button
             */
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color8.setValue(Color.web("#000"));
                    Color c1=color8.getValue();
                    MainPage.colours[7]=c1;
                    stage.close();
                }
            });
        }
        //if selected colour is similar to any of the other colour of the color picker
        if(c.equals(MainPage.colours[1]) ||c.equals(MainPage.colours[2]) || c.equals(MainPage.colours[3])||c.equals(MainPage.colours[4])||c.equals(MainPage.colours[5]) ||c.equals(MainPage.colours[6]) ||c.equals(MainPage.colours[0])  )
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color8.setValue(Color.web("#000"));
                Color c1=color8.getValue();
                MainPage.colours[7]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error.fxml"));
            Scene scene=new Scene(pane);
            stage.setScene(scene);
            stage.show();
            /*
            Exit error window on pressing ESCAPE button
             */
            scene.addEventHandler(KeyEvent.KEY_PRESSED,event1 -> {
                if(event1.getCode()==KeyCode.ESCAPE)
                {
                    color8.setValue(Color.web("#000"));
                    Color c1=color8.getValue();
                    MainPage.colours[7]=c1;
                    stage.close();
                }
            });
        }

    }



    /**
     * Initializing the values in the color picker whenever the Settings page is entered
     */
    @FXML
    public void initialize() {
        assert settingsPane != null : "fx:id=\"settingsPane\" was not injected: check your FXML file 'settings.fxml'.";
        assert color1 != null : "fx:id=\"color1\" was not injected: check your FXML file 'settings.fxml'.";
        assert color2 != null : "fx:id=\"color2\" was not injected: check your FXML file 'settings.fxml'.";
        assert color3 != null : "fx:id=\"color3\" was not injected: check your FXML file 'settings.fxml'.";
        assert color4 != null : "fx:id=\"color4\" was not injected: check your FXML file 'settings.fxml'.";
        assert color5 != null : "fx:id=\"color5\" was not injected: check your FXML file 'settings.fxml'.";
        assert color6 != null : "fx:id=\"color6\" was not injected: check your FXML file 'settings.fxml'.";
        assert color7 != null : "fx:id=\"color7\" was not injected: check your FXML file 'settings.fxml'.";
        assert color8 != null : "fx:id=\"color8\" was not injected: check your FXML file 'settings.fxml'.";
        /*Setting value for 8 color picker
        corresponding to 8 players
         */
        color1.setValue(MainPage.colours[0]);
        color2.setValue(MainPage.colours[1]);
        color3.setValue(MainPage.colours[2]);
        color4.setValue(MainPage.colours[3]);
        color5.setValue(MainPage.colours[4]);
        color6.setValue(MainPage.colours[5]);
        color7.setValue(MainPage.colours[6]);
        color8.setValue(MainPage.colours[7]);
    }
}
