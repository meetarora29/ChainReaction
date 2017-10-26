import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.awt.*;

class myRectangle extends Rectangle {
    Point p;

    myRectangle(int x, int y) {
        p=new Point(x, y);
    }
}

class Ball extends Circle {
    int mass;
    Color color;

    Ball(Ball b) {
        mass=b.mass;
        color=b.color;
    }

    Ball(Color c) {
        color=c;
        mass=1;
    }

    int getMass() {
        return mass;
    }

    void increaseMass() {
        mass++;
    }
}

public class GamePage extends Application {

    public static GridPane grid=new GridPane();
    private static Grid g;
    public static Stage stage;

    // Make grid outline
    private void buildGrid(myRectangle[][] box, int n, int m) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                myRectangle r=new myRectangle(i, j);
                r.setWidth(50);
                r.setHeight(50);
                r.setStroke(Color.LIGHTGRAY);
                r.setStrokeWidth(1);
                r.setFill(Color.WHITE);

                r.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    System.out.println(r.p);
                    if(g.noAnimation())
                        g.setPosition(r.p.x, r.p.y);
                });

                box[i][j]=r;
            }
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                grid.add(box[i][j], j, i);
            }
        }
    }

    private void buildButtons(BorderPane borderPane) {
        Button button1=new Button("Save");
        Button button2=new Button("Undo");

        ComboBox<String> comboBox=new ComboBox<>();
        comboBox.setPromptText("Choose Option");
        comboBox.getItems().addAll("Restart Game", "Return to Main Menu");
        comboBox.setOnAction(event -> System.out.println(comboBox.getValue()));

        HBox hBox=new HBox();
        Pane spacer=new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        hBox.getChildren().addAll(button1, spacer, comboBox);

        borderPane.setTop(hBox);
        borderPane.setBottom(button2);

        BorderPane.setMargin(hBox, new Insets(10, 10, 10, 10));
        BorderPane.setMargin(button2, new Insets(10, 10, 10, 10));
        BorderPane.setMargin(grid, new Insets(0, 10, 0, 10));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialisations
        stage=primaryStage;
        int n=10, m=10, numPlayers=2;
        myRectangle[][] box=new myRectangle[n][m];  // For grid outline
        Player[] players=new Player[numPlayers];
        Color[] colors=new Color[numPlayers];
        g=Player.setGrid(n, m, grid, players);
        BorderPane borderPane=new BorderPane(grid);

        // Setting Color Array
        colors[0]=Color.BLUE;
        colors[1]=Color.RED;

        for(int i=0;i<numPlayers;i++)
            players[i]=new Player(colors[i]);

        // GridPane properties
        grid.setMinSize(500, 500);
        grid.setAlignment(Pos.CENTER);

        buildGrid(box, n, m);
        buildButtons(borderPane);

        Scene scene=new Scene(borderPane);

        stage.setTitle("Chain Reaction");
        stage.setScene(scene);
        stage.show();
    }
}
