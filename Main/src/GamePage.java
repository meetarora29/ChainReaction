import javafx.application.Application;
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
    Player player;

    myRectangle(int x, int y) {
        p=new Point(x, y);
        player=new Player(Color.WHITE);
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

    void increaseMass() {
        mass++;
    }
}

public class GamePage extends Application {

    public static GridPane grid=new GridPane();
    public static Grid g;

    // Make grid outline
    public void buildGrid(myRectangle[][] box, int n, int m) {
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
                    g.setPosition(r.p.x, r.p.y, r.player);
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
        int n=10, m=10;
        g=new Grid(n, m, grid);
        myRectangle[][] box=new myRectangle[n][m];  // For grid outline
        grid.setMinSize(500, 500);

        buildGrid(box, n, m);

        Scene scene=new Scene(grid);

        primaryStage.setTitle("Chain Reaction");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
