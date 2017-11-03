import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


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
        print();
        AnchorPane pane= FXMLLoader.load(getClass().getResource("fxml_files/sample_main.fxml"));
        settingsPane.getChildren().setAll(pane);

    }

    @FXML
    void selectColor1(ActionEvent event) {
        Color c=color1.getValue();
        color1.setValue(c);
        MainPage.colours[0]=c;
    }

    @FXML
    void selectColor2(ActionEvent event) {
        Color c=color2.getValue();
        color2.setValue(c);
        MainPage.colours[1]=c;
    }

    @FXML
    void selectColor3(ActionEvent event) {
        Color c=color3.getValue();
        color3.setValue(c);
        MainPage.colours[2]=c;
    }

    @FXML
    void selectColor4(ActionEvent event) {
        Color c=color4.getValue();
        color4.setValue(c);
        MainPage.colours[3]=c;
    }

    @FXML
    void selectColor5(ActionEvent event) {
        Color c=color5.getValue();
        color5.setValue(c);
        MainPage.colours[4]=c;
    }

    @FXML
    void selectColor6(ActionEvent event) {
        Color c=color6.getValue();
        color6.setValue(c);
        MainPage.colours[5]=c;
    }

    @FXML
    void selectColor7(ActionEvent event) {
        Color c=color7.getValue();
        color7.setValue(c);
        MainPage.colours[6]=c;
    }

    @FXML
    void selectColor8(ActionEvent event) {
        Color c=color8.getValue();

        MainPage.colours[7]=c;
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
        color1.setValue(Color.web("#42f4d7"));
        color2.setValue(Color.web("#FA8072"));
        color3.setValue(Color.web("#f44168"));
        color4.setValue(Color.web("#c7f441"));
        color5.setValue(Color.web("#b541f4"));
        color6.setValue(Color.web("#40E0D0"));
        color7.setValue(Color.web("#f5f900"));
        color8.setValue(Color.web("#000"));
    }
}
