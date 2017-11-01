import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.awt.*;
import java.io.*;

class myRectangle extends Rectangle {
    Point p;

    myRectangle(int x, int y) {
        p=new Point(x, y);
    }
}

public class GamePage extends Application {
    private int n, m,numPlayers;
    private static GridPane grid=new GridPane();
    private static Grid g;
    static Stage stage;

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

    // Build UI Elements
    private void buildButtons(BorderPane borderPane) {
        Button button1=new Button("Save");
        button1.setOnAction(event -> {
            try {
                serialize();
            }
            catch (IOException e) {
                System.out.println(e);
            }
//            catch (ClassNotFoundException e) {
//                System.out.println(e);
//            }
        });

        Button button2=new Button("Undo");
        button2.setOnAction(event -> {
            if(g.noAnimation())
                g.undo();
        });
        ComboBox<String> comboBox=new ComboBox<>();
        comboBox.setPromptText("Choose Option");
        comboBox.getItems().addAll("Restart Game", "Return to Main Menu");
        comboBox.setOnAction(event -> System.out.println(comboBox.getValue()));
        comboBox.setOnAction(event -> {
            if(comboBox.getSelectionModel().getSelectedIndex()==0)
                g.restartGame();
            // TODO: ComboBox reset
        });

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

    private void serialize() throws IOException {
        g.serializeMatrix();
        OutputStream outputStream=new FileOutputStream("game.dat");
        ObjectOutputStream out=null;
        try {
            out=new ObjectOutputStream(outputStream);
            out.writeObject(g);
        }
        finally {
            if(out!=null)
                out.close();
        }
    }

    private void deserialize() throws IOException, ClassNotFoundException {
        InputStream input=new FileInputStream("game.dat");
        ObjectInputStream in=null;
        try {
            in=new ObjectInputStream(input);
            g=(Grid)in.readObject();
            grid=g.resolve(grid);
        }
        finally {
            if(in!=null)
                in.close();
        }
    }

    void setN(int n) {
        this.n = n;
    }

    void setM(int m) {
        this.m = m;
    }

    void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

//    @Override
    public void start(Stage primaryStage) {
        // Initialisations
        MainPage.window=primaryStage;

        myRectangle[][] box=new myRectangle[n][m];  // For grid outline
        Player[] players=new Player[numPlayers];
        Color[] colors=new Color[numPlayers];
        g=new Grid(n, m, grid, players);
        BorderPane borderPane=new BorderPane(grid);
        // TODO: Correct Resizing of Window

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

        MainPage.window.setTitle("Chain Reaction");
        MainPage.window.setScene(scene);
        MainPage.window.show();
    }
}
