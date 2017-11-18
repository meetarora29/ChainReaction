import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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

/**
 * myRectangle is a custom class inheriting from Rectangle and contains
 * information of the coordinates in the grid they occupy.
 * The objects of this class will be used to create the visual grid for the game.
 *
 * @author Meet Arora
 */
class myRectangle extends Rectangle {
    Point p;

    /**
     * Class Constructor specifying the coordinates for the cell
     *
     * @param x the x-coordinate of the cell
     * @param y the y-coordinate of the cell
     */
    myRectangle(int x, int y) {
        p=new Point(x, y);
    }
}

/**
 * GamePage is the class used to signify the page the game will be played on.
 * It contains all the components required to make the visual page.
 *
 * @author Meet Arora
 */
class GamePage {
    private static int n, m,numPlayers;
    private static GridPane grid=new GridPane();
    private static Grid g;
    private static myRectangle[][] box;
    static BorderPane borderPane;
    private static Player[] players;
    private HBox hBox1;
    private Label undo_label;

    {
        undo_label=new Label();
        undo_label.setTextFill(Color.GRAY);
        undo_label.setFocusTraversable(false);
    }

    /**
     * Gets the Grid of the class
     *
     * @return the Grid object of the class
     */
    static Grid getGrid() {
        return g;
    }

    /**
     * Sets the variable numPlayers and the Player object array players
     *
     * @param numPlayers the number of players selected
     * @param players the array of Player objects
     */
    static void setPlayers(int numPlayers, Player[] players) {
        GamePage.numPlayers=numPlayers;
        GamePage.players=players;
    }

    /**
     * Sets the label showing the number of undo moves left for the player
     * that just played their chance.
     *
     * @param undo_left is the number of undo moves left for the player that
     *                  just played their chance
     */
    void setUndoLabel(int undo_left) {
        if(undo_left>=0)
            undo_label.setText("Left : "+undo_left);
        else
            undo_label.setText("");
    }

    /**
     * Makes the cells that are valid for the current player have the
     * 'click' cursor on hover
     *
     * @param i the row index of the cell
     * @param j the column index of the cell
     */
    static void makeBoxClickable(int i, int j) {
        box[i][j].getStyleClass().add("clickable");
    }

    /**
     * Makes the cells that are invalid for the current player have the
     * 'default' cursor on hover
     *
     * @param i the row index of the cell
     * @param j the column index of the cell
     */
    static void makeBoxUnclickable(int i, int j) {
        box[i][j].getStyleClass().removeAll("clickable");
    }

    /**
     * Gives focus to the cell in the ith row and jth column.
     * Mainly used to signify which cell the computer played on.
     *
     * @param i the row index of the cell
     * @param j the column index of the cell
     */
    static void setFocus(int i, int j) {
        box[i][j].requestFocus();
    }

    /**
     * Makes the grid that will be used for the game.
     * The cells are given all the required properties as well as event handlers.
     * Once made, they are added to the gridpane.
     *
     * @param box the 2D array of myRectangle type that are used to create the visual grid
     * @param n the number of rows
     * @param m the number of columns
     */
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
                    if(g.noAnimation() && event.getCode().equals(KeyCode.ENTER) && !event.isShiftDown())
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

    /**
     * Removes the visual grid when changing scenes so that no part of a previous
     * grid remains on resuming or starting a new game.
     */
    static void destroyGrid() {
        ArrayList<Node> list=new ArrayList<>();
        list.addAll(grid.getChildren());
        grid.getChildren().removeAll(list);
    }

    /**
     * Adds the required event handlers for the combobox choices.
     * Also adds fade out effect on the BorderPane.
     *
     * @param comboBox the ComboBox whose choices need the appropriate event handlers
     * @param borderPane the BorderPane to which the fade out effect is added
     * @param stage the Stage being used on the game page
     */
    private void addComboboxEvents(ComboBox<String> comboBox, BorderPane borderPane, Stage stage) {
        if (comboBox.getSelectionModel().getSelectedIndex() == 1) {
            MainPage mainPage = new MainPage();
            try {
                serialize();
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), borderPane);
                fadeTransition.setToValue(0);
                fadeTransition.play();
                fadeTransition.setOnFinished(event -> {
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
        }
        else if (comboBox.getSelectionModel().getSelectedIndex() == 0)
            g.restartGame();

        // ComboBox reset
        Platform.runLater(() -> comboBox.setValue(null));
    }

    /**
     * Builds the UI elements on the game page.
     * Makes the button, combobox, and the label that appears below the grid.
     * Also sets the event handlers for mouse clicks as well as key presses.
     *
     * @param borderPane the BorderPane used to align the UI elements
     * @param stage the Stage being used on the game page
     */
    private void buildButtons(BorderPane borderPane, Stage stage) {
        Button button=new Button("Undo");
        button.setOnMouseClicked(event -> {
            if(g.noAnimation())
                g.undo();
            borderPane.requestFocus();
            turnOffKeyboardMode();
        });
        button.setOnKeyPressed(event -> {
            if(g.noAnimation() && event.getCode().equals(KeyCode.ENTER) && !event.isShiftDown())
                g.undo();
        });
        button.getStyleClass().add("focus");

        ComboBox<String> comboBox=new ComboBox<>();
        comboBox.getStyleClass().add("focus");
        comboBox.setPromptText("Choose Option");
        comboBox.getItems().addAll("Restart Game", "Return to Main Menu");
        comboBox.setFocusTraversable(true);
        comboBox.setOnKeyPressed(event -> {
            if(g.noAnimation() && event.getCode().equals(KeyCode.ENTER) && !event.isShiftDown())
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

        Button volume_off=GlyphsDude.createIconButton(FontAwesomeIcon.BELL_ALT);
        Button volume_up=GlyphsDude.createIconButton(FontAwesomeIcon.BELL_SLASH_ALT);
        volume_off.getStyleClass().add("focus");
        volume_up.getStyleClass().add("focus");

        HBox hBox=new HBox();
        Pane spacer=new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        hBox.getChildren().addAll(button, undo_label, spacer, comboBox);
        HBox.setMargin(undo_label, new Insets(5, 10, 10, 10));
        borderPane.setTop(hBox);

        if(g.getSoundMode()==0)
            hBox.getChildren().add(3, volume_up);
        else
            hBox.getChildren().add(3, volume_off);

        HBox.setMargin(volume_off, new Insets(0, 10, 0, 0));
        volume_off.setOnAction(event -> {
            hBox.getChildren().removeAll(volume_off);
            hBox.getChildren().add(3, volume_up);
            borderPane.requestFocus();
            g.toggleSoundMode();
        });
        volume_off.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER) && !event.isShiftDown()) {
                hBox.getChildren().removeAll(volume_off);
                hBox.getChildren().add(3, volume_up);
                volume_up.requestFocus();
                g.toggleSoundMode();
            }
        });

        HBox.setMargin(volume_up, new Insets(0, 10, 0, 0));
        volume_up.setOnAction(event -> {
            hBox.getChildren().removeAll(volume_up);
            hBox.getChildren().add(3, volume_off);
            borderPane.requestFocus();
            g.toggleSoundMode();
        });
        volume_up.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER) && !event.isShiftDown()) {
                hBox.getChildren().removeAll(volume_up);
                hBox.getChildren().add(3, volume_off);
                volume_off.requestFocus();
                g.toggleSoundMode();
            }
        });

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


    /**
     * Changes the colour of the lines of the grid as well the background in
     * accordance with the current player's color.
     *
     * @param color the color of the current player whose turn it is
     */
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

    /**
     * Serializes the state of the Grid object in order to save the current game
     * that's being played.
     *
     * @throws IOException if a generic output error occurs with ObjectOutputStream
     */
    void serialize() throws IOException {
        if (g.getFlag()==0)
            return;

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

    /**
     * Deserializes the serialised file in order to restore the saved state of the
     * game so that the user can resume the game where they left it.
     *
     * @throws IOException if a generic input error occurs with ObjectInputStream
     * @throws ClassNotFoundException if the serialised class is not found when reading back
     */
    void deserialize() throws IOException, ClassNotFoundException {
        InputStream input=new FileInputStream("game.dat");
        ObjectInputStream in=null;
        try {
            in=new ObjectInputStream(input);
            borderPane=new BorderPane(grid);
            setBorderPaneProperties();
            g=(Grid)in.readObject();
            g.resolve(grid, this);
            // GridPane properties
            grid.setMinSize(500, 500);
            grid.setAlignment(Pos.CENTER);

            buildButtons(borderPane, MainPage.window);
            Scene scene=new Scene(borderPane);
            borderPane.requestFocus();
            scene.getStylesheets().add(this.getClass().getResource("css/GamePage.css").toExternalForm());
            MainPage.window.setScene(scene);
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), borderPane);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
        }
        finally {
            if(in!=null)
                in.close();
        }
    }

    /**
     * Sets the number of rows for the grid.
     *
     * @param n the number of rows
     */
    void setN(int n) {
        GamePage.n = n;
    }

    /**
     * Sets the number of columns for the grid.
     *
     * @param m the number of columns
     */
    void setM(int m) {
        GamePage.m = m;
    }

    /**
     * Sets the number of players selected.
     *
     * @param numPlayers the number of players
     */
    void setNumPlayers(int numPlayers) {
        GamePage.numPlayers = numPlayers;
    }

    /**
     * Turns on the keyboard mode so that the game can be played using the
     * keyboard as well and not just the mouse.
     */
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

    /**
     * Turns off the keyboard mode so that the game cannot be influenced by the keyboard.
     * Only the mouse can be used to play the game in this case.
     */
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

    /**
     * Adds event handlers to the BorderPane that will be used to toggle
     * keyboard mode.
     */
    private void setBorderPaneProperties() {
        borderPane.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER) && event.isShiftDown())
                turnOnKeyboardMode();
            else if(event.getCode().equals(KeyCode.ESCAPE))
                turnOffKeyboardMode();
        });
    }

    /**
     * The main function for the game page that initialises the required properties
     * for the game to start, builds the visual components, and displays once completed.
     *
     * @param primaryStage is the Stage on which the visuals will be shown
     */
    void start(Stage primaryStage) {
        // Initialisations
        MainPage.window=primaryStage;
        box=new myRectangle[n][m];  // For grid outline
        players = new Player[numPlayers];
        if(numPlayers==1)
            players = new Player[2];
        Color[] colors;
        Grid.resetInstance();
        g=Grid.getInstance(n, m, grid, players, this);
        borderPane=new BorderPane(grid);
        setBorderPaneProperties();

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

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), borderPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
}
