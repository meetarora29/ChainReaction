import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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

    private static GridPane grid=new GridPane();
    private static Grid g;

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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialisations
        int n=10, m=10, numPlayers=2;
        myRectangle[][] box=new myRectangle[n][m];  // For grid outline
        Player[] players=new Player[numPlayers];
        Color[] colors=new Color[numPlayers];
        g=Player.setGrid(n, m, grid, players);

        // Setting Color Array
        colors[0]=Color.BLUE;
        colors[1]=Color.RED;

        for(int i=0;i<numPlayers;i++)
            players[i]=new Player(colors[i]);

        grid.setMinSize(500, 500);
        grid.setAlignment(Pos.CENTER);

        buildGrid(box, n, m);

        Scene scene=new Scene(grid);

        primaryStage.setTitle("Chain Reaction");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
