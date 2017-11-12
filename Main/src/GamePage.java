import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

class myRectangle extends Rectangle {
    Point p;

    myRectangle(int x, int y) {
        p=new Point(x, y);
    }
}

class GamePage {
    private static int n, m,numPlayers;
    private static GridPane grid=new GridPane();
    private static Grid g;
    private static myRectangle[][] box;
    private static BorderPane borderPane;
    private static Player[] players;
    private HBox hBox1;

    static Grid getGrid() {
        return g;
    }

    static void setPlayers(int numPlayers, Player[] players) {
        GamePage.numPlayers=numPlayers;
        GamePage.players=players;
    }

    static void makeBoxClickable(int i, int j) {
        box[i][j].getStyleClass().add("clickable");
    }

    static void makeBoxUnclickable(int i, int j) {
        box[i][j].getStyleClass().removeAll("clickable");
    }

    // Make grid outline
    static void buildGrid(myRectangle[][] box, int n, int m) {
        GamePage.box=box;
        GamePage.n=n;
        GamePage.m=m;

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                myRectangle r=new myRectangle(i, j);
                r.setWidth(50);
                r.setHeight(50);
                r.setArcHeight(15);
                r.setArcWidth(15);
                r.setStroke(Color.LIGHTGRAY);
                r.setStrokeWidth(1);
                r.setFill(Color.WHITE);
                r.getStyleClass().add("clickable");

                r.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    if(g.noAnimation())
                        g.setPosition(r.p.x, r.p.y);
                });
                r.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                    if(g.noAnimation() && event.getCode().equals(KeyCode.ENTER))
                        g.setPosition(r.p.x, r.p.y);
                });
                r.focusedProperty().addListener((observable, oldValue, newValue) -> {
                    if(newValue)
                        r.setFill(Color.LIGHTGRAY);
                    else
                        r.setFill(Color.WHITE);
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

    // Destroy Grid
    static void destroyGrid() {
        ArrayList<Node> list=new ArrayList<>();
        list.addAll(grid.getChildren());
        grid.getChildren().removeAll(list);
    }

    private void addComboboxEvents(ComboBox<String> comboBox, BorderPane borderPane, Stage stage) {
        if (comboBox.getSelectionModel().getSelectedIndex() == 1) {
            MainPage mainPage = new MainPage();
            try {
                serialize();
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), borderPane);
                fadeTransition.setToValue(0);
                fadeTransition.play();
                fadeTransition.setOnFinished(event1 -> {
                    try {
                        destroyGrid();
                        mainPage.start(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                System.out.println();
            }
        } else if (comboBox.getSelectionModel().getSelectedIndex() == 0) {
            g.restartGame();

            // ComboBox reset
            Platform.runLater(() -> comboBox.setValue(null));
        }
    }

    // Build UI Elements
    private void buildButtons(BorderPane borderPane, Stage stage) {
        Button button=new Button("Undo");
        button.setOnMouseClicked(event -> {
            if(g.noAnimation())
                g.undo();
            borderPane.requestFocus();
        });
        button.setOnKeyPressed(event -> {
            if(g.noAnimation() && event.getCode().equals(KeyCode.ENTER))
                g.undo();
        });
        button.getStyleClass().add("focus");

        ComboBox<String> comboBox=new ComboBox<>();
        comboBox.getStyleClass().add("focus");
        comboBox.setPromptText("Choose Option");
        comboBox.getItems().addAll("Restart Game", "Return to Main Menu");
        comboBox.setFocusTraversable(true);
        comboBox.setOnKeyPressed(event -> {
            if(g.noAnimation() && event.getCode().equals(KeyCode.ENTER))
                addComboboxEvents(comboBox, borderPane, stage);
        });
        comboBox.setCellFactory(event -> {
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item);
                }
            };
            cell.setOnMousePressed(e -> {
                if (!cell.isEmpty() && g.noAnimation())
                    addComboboxEvents(comboBox, borderPane, stage);
            });
            return cell ;
        });

        HBox hBox=new HBox();
        Pane spacer=new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        hBox.getChildren().addAll(button, spacer, comboBox);
        borderPane.setTop(hBox);

        hBox1=new HBox();
        Label label=new Label("Press Shift+Enter to turn on Keyboard Mode");
        label.setTextFill(Color.GRAY);
        hBox1.getChildren().add(label);
        hBox1.setAlignment(Pos.CENTER);
        borderPane.setBottom(hBox1);

        BorderPane.setMargin(hBox, new Insets(10, 10, 10, 10));
        BorderPane.setMargin(hBox1, new Insets(25, 10, 10, 10));
        BorderPane.setMargin(grid, new Insets(0, 20, 0, 20));
    }


    static void changeGridLineColor(Color color) {
        for(int i=0;i<n;i++)
            for (int j = 0; j < m; j++)
                box[i][j].setStroke(color);
        int r=(int)(color.getRed()*255);
        int g=(int)(color.getGreen()*255);
        int b=(int)(color.getBlue()*255);

        // Background
        borderPane.setStyle("-fx-background-color: rgb(" + r +","+ g + ","+ b+");");
    }

    void serialize() throws IOException {
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

    void deserialize() throws IOException, ClassNotFoundException {
        InputStream input=new FileInputStream("game.dat");
        ObjectInputStream in=null;
        try {
            in=new ObjectInputStream(input);
            borderPane=new BorderPane(grid);
            setBorderPaneProperties();
            g=(Grid)in.readObject();
            grid=g.resolve(grid);
            // GridPane properties
            grid.setMinSize(500, 500);
            grid.setAlignment(Pos.CENTER);

            buildButtons(borderPane, MainPage.window);
            Scene scene=new Scene(borderPane);
            borderPane.requestFocus();
            scene.getStylesheets().add(this.getClass().getResource("css/GamePage.css").toExternalForm());
            MainPage.window.setScene(scene);
        }
        finally {
            if(in!=null)
                in.close();
        }
    }

    void setN(int n) {
        GamePage.n = n;
    }

    void setM(int m) {
        GamePage.m = m;
    }

    void setNumPlayers(int numPlayers) {
        GamePage.numPlayers = numPlayers;
    }

    private void turnOnKeyboardMode() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++)
                box[i][j].setFocusTraversable(true);
        }
        box[0][0].requestFocus();
        Label label=new Label("Press Escape to turn off Keyboard Mode");
        label.setTextFill(Color.GRAY);
        hBox1.getChildren().removeAll(hBox1.getChildren());
        hBox1.getChildren().add(label);
    }

    private void turnOffKeyboardMode() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++)
                box[i][j].setFocusTraversable(false);
        }
        borderPane.requestFocus();
        Label label=new Label("Press Shift+Enter to turn on Keyboard Mode");
        label.setTextFill(Color.GRAY);
        hBox1.getChildren().removeAll(hBox1.getChildren());
        hBox1.getChildren().add(label);
    }

    private void setBorderPaneProperties() {
        borderPane.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER) && event.isShiftDown())
                turnOnKeyboardMode();
            else if(event.getCode().equals(KeyCode.ESCAPE))
                turnOffKeyboardMode();
        });
    }

    void start(Stage primaryStage) {
        // Initialisations
        MainPage.window=primaryStage;
        box=new myRectangle[n][m];  // For grid outline
        players = new Player[numPlayers];
        if(numPlayers==1)
            players = new Player[2];
        Color[] colors;
        g=new Grid(n, m, grid, players);
        borderPane=new BorderPane(grid);
        setBorderPaneProperties();
        // TODO: Correct Resizing of Window

        colors=MainPage.getColours();

        for(int i=0;i<numPlayers;i++)
            players[i]=new Player(colors[i]);

        // Computer
        if(numPlayers==1) {
            players[1] = new Computer(colors[1]);
            g.setComputerMode();
        }

        // GridPane properties
        grid.setMinSize(500, 500);
        grid.setAlignment(Pos.CENTER);

        buildGrid(box, n, m);
        buildButtons(borderPane, primaryStage);
        changeGridLineColor(players[0].getColor());

        Scene scene=new Scene(borderPane);
        borderPane.requestFocus();
        scene.getStylesheets().add(this.getClass().getResource("css/GamePage.css").toExternalForm());

        MainPage.window.setTitle("Chain Reaction");
        MainPage.window.setScene(scene);
        MainPage.window.show();
    }
}
