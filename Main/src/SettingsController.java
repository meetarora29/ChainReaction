import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.awt.Color.*;

public class SettingsController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane settingsPane;

    @FXML
    private ColorPicker color1;

    @FXML
    private ColorPicker color2;

    @FXML
    private ColorPicker color3;

    @FXML
    private ColorPicker color4;

    @FXML
    private ColorPicker color5;

    @FXML
    private ColorPicker color6;

    @FXML
    private ColorPicker color7;

    @FXML
    private ColorPicker color8;

    @FXML
    void clickDone(ActionEvent event) throws IOException {
//        color1.setValue(MainPage.colours[0]);


        color1.setValue(MainPage.colours[0]);
        color2.setValue(MainPage.colours[1]);
        color3.setValue(MainPage.colours[2]);
        color4.setValue(MainPage.colours[3]);
        color5.setValue(MainPage.colours[4]);
        color6.setValue(MainPage.colours[5]);
        color7.setValue(MainPage.colours[6]);
        color8.setValue(MainPage.colours[7]);
//        print();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/sample_main.fxml"));
        settingsPane.getChildren().setAll(pane);

    }

    @FXML
    void selectColor1(ActionEvent event) throws IOException {
        Color c=color1.getValue();

        MainPage.colours[0]=c;
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setTitle("Error");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setOnCloseRequest(event1 -> {
                color1.setValue(Color.web("#42f4d7"));
                Color c1=color1.getValue();
                MainPage.colours[0]=c1;
            });
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }
        if(c.equals(MainPage.colours[1]) ||c.equals(MainPage.colours[2]) || c.equals(MainPage.colours[3])||c.equals(MainPage.colours[4])||c.equals(MainPage.colours[5]) ||c.equals(MainPage.colours[6]) ||c.equals(MainPage.colours[7])  )
        {
            AnchorPane pane;
                Stage stage=new Stage();
                stage.setTitle("Error");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setOnCloseRequest(event1 -> {
                    color1.setValue(Color.web("#42f4d7"));
                    Color c1=color1.getValue();
                    MainPage.colours[0]=c1;
                });
                pane=FXMLLoader.load(getClass().getResource("fxml_files/error.fxml"));
                stage.setScene(new Scene(pane));
                stage.show();
        }


    }

    @FXML
    void selectColor2(ActionEvent event) throws IOException {
        Color c=color2.getValue();
        MainPage.colours[1]=c;
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color2.setValue(Color.web("#FA8072"));
                Color c1=color2.getValue();
                MainPage.colours[1]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }
        if(c.equals(MainPage.colours[0]) ||c.equals(MainPage.colours[2]) || c.equals(MainPage.colours[3])||c.equals(MainPage.colours[4])||c.equals(MainPage.colours[5]) ||c.equals(MainPage.colours[6]) ||c.equals(MainPage.colours[7])  )
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color2.setValue(Color.web("#FA8072"));
                Color c1=color2.getValue();
                MainPage.colours[1]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }

    }

    @FXML
    void selectColor3(ActionEvent event) throws IOException {
        Color c=color3.getValue();
        MainPage.colours[2]=c;
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color3.setValue(Color.web("#f44168"));
                Color c1=color3.getValue();
                MainPage.colours[2]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }
        if(c.equals(MainPage.colours[1]) ||c.equals(MainPage.colours[0]) || c.equals(MainPage.colours[3])||c.equals(MainPage.colours[4])||c.equals(MainPage.colours[5]) ||c.equals(MainPage.colours[6]) ||c.equals(MainPage.colours[7])  )
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color3.setValue(Color.web("#f44168"));
                Color c1=color3.getValue();
                MainPage.colours[2]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }

    }


    @FXML
    void selectColor4(ActionEvent event) throws IOException {
        Color c=color4.getValue();
        MainPage.colours[3]=c;
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color4.setValue(Color.web("#1a3399"));
                Color c1=color4.getValue();
                MainPage.colours[3]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }
        if(c.equals(MainPage.colours[1]) ||c.equals(MainPage.colours[2]) || c.equals(MainPage.colours[0])||c.equals(MainPage.colours[4])||c.equals(MainPage.colours[5]) ||c.equals(MainPage.colours[6]) ||c.equals(MainPage.colours[7])  )
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color4.setValue(Color.web("#c7f441"));
                Color c1=color4.getValue();
                MainPage.colours[3]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }

    }

    @FXML
    void selectColor5(ActionEvent event) throws IOException {
        Color c=color5.getValue();
        MainPage.colours[4]=c;
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color5.setValue(Color.web("#b541f4"));
                Color c1=color5.getValue();
                MainPage.colours[4]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }
        if(c.equals(MainPage.colours[1]) ||c.equals(MainPage.colours[2]) || c.equals(MainPage.colours[3])||c.equals(MainPage.colours[0])||c.equals(MainPage.colours[5]) ||c.equals(MainPage.colours[6]) ||c.equals(MainPage.colours[7])  )
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color5.setValue(Color.web("#b541f4"));
                Color c1=color5.getValue();
                MainPage.colours[4]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }

    }

    @FXML
    void selectColor6(ActionEvent event) throws IOException {
        Color c=color6.getValue();
        MainPage.colours[5]=c;
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color6.setValue(Color.web("#40E0D0"));
                Color c1=color6.getValue();
                MainPage.colours[5]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }
        if(c.equals(MainPage.colours[1]) ||c.equals(MainPage.colours[2]) || c.equals(MainPage.colours[3])||c.equals(MainPage.colours[4])||c.equals(MainPage.colours[0]) ||c.equals(MainPage.colours[6]) ||c.equals(MainPage.colours[7])  )
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color6.setValue(Color.web("#40E0D0"));
                Color c1=color6.getValue();
                MainPage.colours[5]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }

    }

    @FXML
    void selectColor7(ActionEvent event) throws IOException {
        Color c=color7.getValue();
        MainPage.colours[6]=c;
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color7.setValue(Color.web("#f5f900"));
                Color c1=color7.getValue();
                MainPage.colours[6]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }
        if(c.equals(MainPage.colours[1]) ||c.equals(MainPage.colours[2]) || c.equals(MainPage.colours[3])||c.equals(MainPage.colours[4])||c.equals(MainPage.colours[5]) ||c.equals(MainPage.colours[0]) ||c.equals(MainPage.colours[7])  )
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color7.setValue(Color.web("#f5f900"));
                Color c1=color7.getValue();
                MainPage.colours[6]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }

    }

    @FXML
    void selectColor8(ActionEvent event) throws IOException {
        Color c=color8.getValue();
        MainPage.colours[7]=c;
        if(c.equals(Color.WHITE))
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color8.setValue(Color.web("#000"));
                Color c1=color8.getValue();
                MainPage.colours[7]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error2.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }
        if(c.equals(MainPage.colours[1]) ||c.equals(MainPage.colours[2]) || c.equals(MainPage.colours[3])||c.equals(MainPage.colours[4])||c.equals(MainPage.colours[5]) ||c.equals(MainPage.colours[6]) ||c.equals(MainPage.colours[0])  )
        {
            AnchorPane pane;
            Stage stage=new Stage();
            stage.setTitle("Error");
            stage.setOnCloseRequest(event1 -> {
                color8.setValue(Color.web("#000"));
                Color c1=color8.getValue();
                MainPage.colours[7]=c1;
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            pane=FXMLLoader.load(getClass().getResource("fxml_files/error.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }

    }



    private void print()
    {
        for(int i=0;i<8;i++)
        {
            System.out.println(MainPage.colours[i]);
        }
    }
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
