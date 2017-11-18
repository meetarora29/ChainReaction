import javafx.animation.*;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Grid class is the main class for the game.
 * It supports all the features and properties of the game.
 * It contains the players, the balls, media players, stacks that
 * are required for the functioning of the game.
 *
 * @author Meet Arora
 */
public class Grid implements Serializable {
    private static Grid instance=null;
    private int n, m;
    private transient GamePage gamePage;
    private transient Ball[][] matrix, prev_state;
    private SerializableBall[][] s_matrix;
    private transient Color color;
    private transient GridPane grid;
    private Player[] players;
    static Stage stage;
    private int curr_player;
    private int numPlayers;
    private int flag;
    private int animation_count, animation_frequency, animation_threshold;
    private boolean notEnded;
    private transient myStack<Ball[][]> moveStack;
    private int load, count, computerMode, soundMode;
    private transient Media media, error_media;
    private transient MediaPlayer mediaPlayer, error_player;
    transient MediaPlayer win_player;

    private static final long serialVersionUID = 1L;

    /**
     * Constructor using values of number of rows, number of columns, the GridPane,
     * and the Players array. It also stores the reference to the GamePage object.
     *
     * @param n is the number of rows
     * @param m is the number of columns
     * @param grid is the GridPane which will be used for the layout
     * @param players is the array of Players playing the game
     * @param gamePage is the GamePage object
     */
    private Grid(int n, int m, GridPane grid, Player[] players, GamePage gamePage) {
        this.n=n;
        this.m=m;
        this.gamePage = gamePage;
        matrix=new Ball[n][m];
        s_matrix=new SerializableBall[n][m];
        this.grid=grid;
        this.players=players;
        numPlayers=players.length;
        moveStack=new myStack<>(3*numPlayers);
        count=0;
        soundMode=1;
        media=new Media(getClass().getResource("sounds/pop.mp3").toExternalForm());
        mediaPlayer=new MediaPlayer(media);
        error_media=new Media(getClass().getResource("sounds/NO.mp3").toExternalForm());
        error_player=new MediaPlayer(error_media);
        notEnded=true;
        if(n==9)
            animation_threshold=500;
        else
            animation_threshold=1000;
    }

    /**
     * Making the Grid class as singleton.
     *
     * @param n is the number of rows
     * @param m is the number of columns
     * @param grid is the GridPane which will be used for the layout
     * @param players is the array of Players playing the game
     * @param gamePage is the GamePage object
     * @return the sole Grid instance of the class
     */
    static Grid getInstance(int n, int m, GridPane grid, Player[] players, GamePage gamePage) {
        if(instance==null)
            instance=new Grid(n, m, grid, players, gamePage);
        return instance;
    }

    /**
     * Reset the object so that a new game doesn't have an old instance.
     */
    static void resetInstance() {
        if(instance!=null)
            instance=null;
    }

    /**
     * Returns a boolean value signifying whether the game has ended or not.
     *
     * @return true if the game has not ended and false otherwise
     */
    boolean isNotEnded() {
        return notEnded;
    }

    /**
     * Gets the flag value which signifies whether the first move has been made.
     *
     * @return flag which is an integer signifying the first move of the game
     */
    int getFlag() {
        return flag;
    }

    /**
     * Sets the computer mode if selected which happens when a single
     * player wants to play.
     */
    void setComputerMode() {
        computerMode=1;
    }

    /**
     * Gets soundMode which is 1 when sounds are playing and 0 when not.
     *
     * @return soundMode which is an integer representing whether sounds are muted or not
     */
    int getSoundMode() {
        return soundMode;
    }

    /**
     * Toggles sound mode. Turns on sound mode if off and vice-versa.
     */
    void toggleSoundMode() {
        if(soundMode==0)
            soundMode=1;
        else
            soundMode=0;
    }

    /**
     * Turns the grid matrix into a serializable version so that the
     * state of the game can be saved.
     */
    void serializeMatrix() {
        s_matrix=new SerializableBall[n][m];
        for(int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j]!=null)
                    s_matrix[i][j] = new SerializableBall(matrix[i][j]);
            }
        }
        load=0;
    }

    /**
     * Makes visual nodes ie balls on the grid using the values of row index,
     * column index provided and using the grid which specifies the properties
     * of the nodes at the given indices.
     *
     * @param i is the row index of the cell
     * @param j is the column index of the cell
     * @param matrix is the grid of balls containing the properties of the cells
     */
    private void makeNode(int i, int j, Ball[][] matrix) {
        if(matrix[i][j]!=null) {
            if(matrix[i][j].getMass()==1) {
                makeCircle(matrix[i][j], i, j, matrix[i][j].getColor());
                if(isCorner(i, j))
                    rotateCorner(i, j, matrix);
            }
            else if(matrix[i][j].getMass()==2 && !isCorner(i, j)) {
                makeCircle(matrix[i][j], i, j, matrix[i][j].getColor());
                makeCircle(matrix[i][j].two, i, j, matrix[i][j].getColor());
                matrix[i][j].setTranslateX(-7.5);
                matrix[i][j].two.setTranslateX(7.5);
                rotateTwo(i, j, matrix);
            }
            else if(matrix[i][j].getMass()==3 && !isExtremeSide(i, j)) {
                makeCircle(matrix[i][j], i, j, matrix[i][j].getColor());
                makeCircle(matrix[i][j].two, i, j, matrix[i][j].getColor());
                makeCircle(matrix[i][j].three, i, j, matrix[i][j].getColor());
                matrix[i][j].setTranslateX(-7.5);
                matrix[i][j].two.setTranslateX(7.5);
                matrix[i][j].setTranslateY(-7.5);
                matrix[i][j].two.setTranslateY(-7.5);
                matrix[i][j].three.setTranslateY(7.5);
                rotateThree(i, j, matrix);
            }
        }
    }

    /**
     * Fills up the GridPane with the appropriate state of the saved game on resuming.
     * It then serves as the layout for the scene of the game page.
     *
     * @param grid is the GridPane which will serve as the layout for the game page
     * @param game is the reference to the GamePage object
     */
    void resolve(GridPane grid, GamePage game) {
        // Transient Initialisations
        matrix=new Ball[n][m];
        this.grid=grid;
        moveStack=new myStack<>(3*numPlayers);
        load=1;
        media=new Media(getClass().getResource("sounds/pop.mp3").toExternalForm());
        mediaPlayer=new MediaPlayer(media);
        error_media=new Media(getClass().getResource("sounds/NO.mp3").toExternalForm());
        error_player=new MediaPlayer(error_media);
        gamePage=game;

        myRectangle[][] box=new myRectangle[n][m];
        GamePage.buildGrid(box, n, m);
        GamePage.setPlayers(numPlayers, players);

        // Resolve Players
        for(int i=0;i<numPlayers;i++)
            players[i].makeColor();

        // Resolve Matrix
        for(int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                if (s_matrix[i][j] != null)
                    matrix[i][j] = new Ball(s_matrix[i][j]);
            }
        }
        moveStack.push(matrix);

        // Resolve GridPane
        removeGridNodes();
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                makeNode(i, j, matrix);
            }
        }

        GamePage.changeGridLineColor(players[curr_player].getColor());
        makeClickable();
        makeUnclickable();
    }

    /**
     * Removes all the nodes from the visual grid so that no nodes from a
     * previous version of the grid can be seen during the current game.
     */
    private void removeGridNodes() {
        ArrayList<Node> list=new ArrayList<>();
        for(Node node : grid.getChildren()) {
            if(node instanceof Circle || node instanceof Pane)
                list.add(node);
        }
        grid.getChildren().removeAll(list);
    }

    /**
     * Returns the index of the previous player that has not lost yet.
     *
     * @return index of the previous player
     */
    private int returnPrevPlayer() {
        int i=curr_player-1;

        while(i>=0) {
            if(players[i].hasLost())
                i--;
            else
                break;
        }

        if(i==-1)
            i=numPlayers-1;
        while (i>curr_player) {
            if (players[i].hasLost())
                i--;
            else
                break;
        }

        return i;
    }

    /**
     * Restarts the game by resetting all the values.
     */
    void restartGame() {
        // If no move has been made
        if (flag==0)
            return;

        removeGridNodes();
        matrix=new Ball[n][m];
        s_matrix=new SerializableBall[n][m];
        flag=0;
        animation_count=0;
        curr_player=0;
        moveStack=new myStack<>(3*numPlayers);
        count=0;
        load=0;
        animation_frequency=0;
        notEnded=true;
        for(int i=0;i<numPlayers;i++) {
            players[i].resetUndo();
            players[i].setTakenTurn(false);
            players[i].setHasLost(false);
        }
        GamePage.changeGridLineColor(players[curr_player].getColor());
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                GamePage.makeBoxClickable(i, j);
        gamePage.setUndoLabel(-1);

        if(computerMode==1)
            GamePage.borderPane.requestFocus();

        // Delete File
        File file=new File("game.dat");
        file.delete();
    }

    /**
     * Sets the grid matrix to a previous state and thus performing an undo.
     * This would only work if there is indeed a previous state to go to as well as
     * the player has undo moves left.
     */
    void undo() {
        if(moveStack.isEmpty() || (computerMode==0 && players[returnPrevPlayer()].getUndo()<=0))
            return;

        if(moveStack.size()==1 && load==1)
            return;

        if(moveStack.size()==1 && load==0 && count<=1) {
            restartGame();
            return;
        }

        players[returnPrevPlayer()].undo();
        prev_state=moveStack.pop();
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(matrix[i][j]!=null)
                    grid.getChildren().removeAll(matrix[i][j], matrix[i][j].two, matrix[i][j].three, matrix[i][j].stackPane);
                makeNode(i, j, prev_state);
            }
        }

        matrix=prev_state;
        count--;
        prevPlayer();

        if(moveStack.size()==1 && load==1)
            gamePage.setUndoLabel(-1);
        if(moveStack.empty())
            gamePage.setUndoLabel(-1);
    }

    /**
     * Saves the previous state of the grid in a stack so undo moves
     * can be performed.
     */
    private void saveState() {
        prev_state=new Ball[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(matrix[i][j]!=null)
                    prev_state[i][j]=new Ball(matrix[i][j]);
            }
        }
        moveStack.push(prev_state);
    }

    /**
     * Makes all those cells and nodes within those cells have the 'default'
     * mouse cursor on hover that belong to a different player.
     */
    private void makeUnclickable() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(matrix[i][j]!=null && !matrix[i][j].getColor().equals(players[curr_player].getColor())) {
                    matrix[i][j].getStyleClass().removeAll("clickable");
                    matrix[i][j].stackPane.getStyleClass().removeAll("clickable");
                    GamePage.makeBoxUnclickable(i ,j);
                }
            }
        }
    }

    /**
     * Makes all the cells and nodes withing those cells that are valid for
     * the current player have the 'click' mouse cursor on hover.
     */
    private void makeClickable() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(matrix[i][j]!=null && matrix[i][j].getColor().equals(players[curr_player].getColor())) {
                    matrix[i][j].getStyleClass().add("clickable");
                    matrix[i][j].stackPane.getStyleClass().add("clickable");
                    GamePage.makeBoxClickable(i, j);
                }
                else if(matrix[i][j]==null)
                    GamePage.makeBoxClickable(i, j);
            }
        }
    }

    /**
     * Returns a boolean signifying whether the player has lost ie the player
     * does not have any balls left on the grid.
     * If flag is 1, the function call has been made from the prevPlayer
     * function and so no update to the player's hasLost value should be
     * made because the player cannot lose on undo.
     *
     * @param flag is an integer value that specifies where the call was made from
     * @param curr_player is an integer value that specifies the index of the player
     * @return true if the player has lost and false otherwise
     */
    private boolean hasPlayerLost(int flag, int curr_player) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(matrix[i][j]!=null && matrix[i][j].getColor().equals(players[curr_player].getColor())) {
                    players[curr_player].setHasLost(false);
                    return false;
                }
            }
        }
        players[curr_player].setHasLost(true);
        if(flag==1)
            players[curr_player].setHasLost(false);
        return true;
    }

    /**
     * Passes the turn to a previous player on undo.
     */
    private void prevPlayer() {
        if(curr_player>0)
            curr_player--;
        else
            curr_player=numPlayers-1;
        GamePage.changeGridLineColor(players[curr_player].getColor());
        makeClickable();
        makeUnclickable();
        if(computerMode==0)
            gamePage.setUndoLabel(players[returnPrevPlayer()].getUndo());

        // If while undo, the player's balls get deleted, mark that player has not taken turn
        if(!players[curr_player].hasLost() && hasPlayerLost(1, curr_player))
            players[curr_player].setTakenTurn(false);

        if(players[curr_player].getClass()==Computer.class)
            undo();
        else if(players[curr_player].hasLost() && players[curr_player].hasTakenTurn())
            prevPlayer();
    }

    /**
     * Passes the turn to the next player once the current player has finished their turn.
     */
    private void nextPlayer() {
        curr_player=(curr_player+1)%numPlayers;
        GamePage.changeGridLineColor(players[curr_player].getColor());
        makeClickable();
        makeUnclickable();
        if(computerMode==0)
            gamePage.setUndoLabel(players[returnPrevPlayer()].getUndo());

        didPlayerLose();

        if(players[curr_player].getClass()==Computer.class)
            players[curr_player].takeTurn(matrix, n, m);
        else if(players[curr_player].hasTakenTurn() && hasPlayerLost(0, curr_player))
            nextPlayer();
    }

    /**
     * Checks if any player has lost in the previous turn.
     * If yes, clears the stack so that undo cannot be done.
     */
    private void didPlayerLose() {
        if(computerMode==0) {
            for(int i=0;i<numPlayers;i++) {
                if(!players[i].hasLost() && players[i].hasTakenTurn()) {
                    if (hasPlayerLost(0, i)) {
                        System.out.println("here"+i);
                        moveStack.clear();
                        moveStack.push(matrix);
                        load=1;
                    }
                }
            }
        }
        if(load==1 && moveStack.size()==1)
            gamePage.setUndoLabel(-1);
    }

    /**
     * Returns a boolean signifying where the cell selected is a valid position
     * for the current player to select.
     *
     * @param i is the row index of the cell
     * @param j is the column index of the cell
     * @return true if the position is valid and false otherwise
     */
    boolean checkValidity(int i, int j) {
        return matrix[i][j] == null || players[curr_player].getColor().equals(matrix[i][j].getColor());
    }

    /**
     * Returns a boolean value signifying whether the current player has
     * won the game by determining if all the balls on the grid belong to
     * the current player.
     *
     * @return true if the current player has won and false otherwise
     */
    private boolean checkWin() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(matrix[i][j]!=null && !matrix[i][j].getColor().equals(color))
                    return false;
            }
        }
        return true;
    }

    /**
     * Returns a boolean value signifying whether any of the specified animations
     * are in progress.
     *
     * @return true if at least one of the specified animations is playing and false otherwise
     */
    boolean noAnimation() { return animation_count==0; }

    /**
     * Checks whether the game is over.
     * If it is, displays the winner and provides with further options the user can select.
     * If not, continues with the game.
     */
    private void isGameOver() {
        if(checkWin() && flag!=0 && (noAnimation() || animation_frequency>animation_threshold)) {
            notEnded=false;
            System.out.println("Game Over!!!");
            // Delete File
            File file=new File("game.dat");
            file.delete();
            stage=new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Congratulations!!!");
            stage.setOpacity(0.85);
            BorderPane pane;
            try {
                Label label=new Label("Player "+(curr_player+1)+" Wins!!!");
                Media winner=new Media(getClass().getResource("sounds/cheering.mp3").toExternalForm());

                // Computer Wins
                if(computerMode==1 && players[curr_player].getClass()==Computer.class) {
                    label = new Label("Computer Wins!!!");
                    winner=new Media(getClass().getResource("sounds/tada.mp3").toExternalForm());
                }

                label.setTextFill(Color.NAVY);
                label.setId("winner");

                pane = FXMLLoader.load(getClass().getResource("fxml_files/game_end.fxml"));
                pane.setCenter(label);
                Scene scene=new Scene(pane);
                scene.getStylesheets().add("css/GameOver.css");
                stage.setScene(scene);
                if(soundMode==1) {
                    win_player = new MediaPlayer(winner);
                    win_player.play();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
            stage.setOnCloseRequest(Event::consume);

        }
        else if (noAnimation())
            nextPlayer();
    }

    /**
     * Sets a current player's node at the selected position only after ensuring
     * that the position is valid.
     *
     * @param i is the row index of the selected cell
     * @param j is the column index of the selected cell
     */
    void setPosition(int i, int j) {
        color=players[curr_player].getColor();
        animation_frequency=0;
        if(!checkValidity(i, j)) {
            // Error Sound
            if(soundMode==1) {
                error_player.play();
                error_player.setOnEndOfMedia(() -> error_player.stop());
            }
            return;
        }
        saveState();
        setMass(i, j);
        players[curr_player].setTakenTurn(true);
        count++;
    }

    /**
     * Gets the mass in the specified cell.
     * If the specified cell has no mass ie there is no ball in the cell, creates
     * a new ball and puts it in the cell before returning the mass.
     *
     * @param i is the row index of the cell
     * @param j is the column index of the cell
     * @return the mass in the cell
     */
    private int getMass(int i, int j) {
        if(matrix[i][j]==null)
            matrix[i][j]=new Ball(color);
        return matrix[i][j].getMass();
    }

    /**
     * Makes the visual balls that are to placed in the grid at the given
     * row index and column index. Also sets the appropriate properties for
     * the ball including the provided color.
     *
     * @param s is the Circle that represents the visual of the ball
     * @param i is the row index of the cell
     * @param j is the column index of the cell
     * @param color is the Color that is filled in the ball
     */
    private void makeCircle(Circle s, int i, int j, Color color) {
        DropShadow dropShadow=new DropShadow(5, Color.GRAY);
        s.setRadius(15);
        s.setFill(color);
        s.setEffect(dropShadow);
        grid.add(s, j, i);
        GridPane.setHalignment(s, HPos.CENTER);
        s.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if(noAnimation())
                setPosition(i, j);
        });
    }

    /**
     * Provides the two-ball animation to the nodes in the cell that
     * is specified by the row index, column index, and the ball matrix
     * provided.
     *
     * @param i is the row index of the cell
     * @param j is the column index of the cell
     * @param matrix is the grid of balls in which the specified cell belongs
     */
    private void rotateTwo(int i, int j, Ball[][] matrix) {
        Pane pane=matrix[i][j].stackPane;
        pane.getChildren().removeAll(matrix[i][j], matrix[i][j].two, matrix[i][j].three);
        pane.getChildren().addAll(matrix[i][j], matrix[i][j].two);
        pane.setMaxSize(50, 50);
        grid.getChildren().removeAll(matrix[i][j], matrix[i][j].two);
        grid.add(pane, j, i);

        int seconds =5;
        if(isExtremeSide(i, j))
            seconds=2;
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(pane.rotateProperty(), 0)),
                new KeyFrame(Duration.seconds(seconds), new KeyValue(pane.rotateProperty(), 360))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Provides the three-ball animation to the nodes in the cell that
     * is specified by the row index, column index, and the ball matrix
     * provided.
     *
     * @param i is the row index of the cell
     * @param j is the column index of the cell
     * @param matrix is the grid of balls in which the specified cell belongs
     */
    private void rotateThree(int i, int j, Ball[][] matrix) {
        Pane pane=matrix[i][j].stackPane;
        pane.getChildren().removeAll(matrix[i][j], matrix[i][j].two, matrix[i][j].three);
        pane.getChildren().addAll(matrix[i][j], matrix[i][j].two, matrix[i][j].three);
        pane.setMaxSize(50, 50);
        grid.getChildren().removeAll(matrix[i][j], matrix[i][j].two, matrix[i][j].three, matrix[i][j].stackPane);
        grid.add(pane, j, i);

        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(pane.rotateProperty(), 0)),
                new KeyFrame(Duration.seconds(2.5), new KeyValue(pane.rotateProperty(), 360))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Provides the corner-ball animation to the nodes in the cell that
     * is specified by the row index, column index, and the ball matrix
     * provided.
     *
     * @param i is the row index of the cell
     * @param j is the column index of the cell
     * @param matrix is the grid of balls in which the specified cell belongs
     */
    private void rotateCorner(int i, int j, Ball[][] matrix) {
        double pixel=0.75;
        TranslateTransition left=new TranslateTransition(Duration.millis(75), matrix[i][j]);
        left.setByX(-pixel);

        TranslateTransition right=new TranslateTransition(Duration.millis(75), matrix[i][j]);
        right.setByX(pixel);

        TranslateTransition up=new TranslateTransition(Duration.millis(75), matrix[i][j]);
        up.setByY(-pixel);

        TranslateTransition down=new TranslateTransition(Duration.millis(75), matrix[i][j]);
        down.setByY(pixel);

        SequentialTransition sequentialTransition=new SequentialTransition(up, down, left, right);
        sequentialTransition.setCycleCount(Animation.INDEFINITE);
        sequentialTransition.play();
    }

    /**
     * Checks whether the cell specified by the row index and the
     * column index is at a corner of the grid.
     *
     * @param i is the row index of the cell
     * @param j is the column index of the cell
     * @return true if the cell is at a corner and false otherwise
     */
    boolean isCorner(int i, int j) {
        if(i==0 && j==0)
            return true;
        if(i==n-1 && j==m-1)
            return true;
        if(i==0 && j==m-1)
            return true;
        if(i==n-1 && j==0)
            return true;
        return false;
    }

    /**
     * Checks whether the cell specified by the row index and
     * the column index lies on an extreme side of the grid.
     *
     * @param i is the row index of the cell
     * @param j is the column index of the cell
     * @return true if the cell is on an extreme side of the grid
     */
    boolean isExtremeSide(int i, int j) {
        if(i==0 || i==n-1 || j==0 || j==m-1)
            return true;
        return false;
    }

    /**
     * Increases mass of the cell that is present at the row index
     * and the column index provided.
     * It then places a visual representation of the node at the
     * designated cell.
     *
     * @param i is the row index of the cell
     * @param j is the column index of the cell
     */
    private void setMass(int i, int j) {
        // Check Invalid Out Of Bounds Index
        if(i<0 || i>n-1 || j<0 || j>m-1)
            return;

        if(matrix[i][j]!=null) {
            if (!matrix[i][j].getColor().equals(color)) {
                // Changing Color
                matrix[i][j].setColor(color);
                FillTransition fillTransition1=new FillTransition(Duration.millis(250));
                fillTransition1.setShape(matrix[i][j]);
                fillTransition1.setToValue(color);

                FillTransition fillTransition2=new FillTransition(Duration.millis(250));
                fillTransition2.setShape(matrix[i][j].two);
                fillTransition2.setToValue(color);

                FillTransition fillTransition3=new FillTransition(Duration.millis(250));
                fillTransition3.setShape(matrix[i][j].three);
                fillTransition3.setToValue(color);

                ParallelTransition parallelTransition=new ParallelTransition(fillTransition1, fillTransition2, fillTransition3);

                parallelTransition.setOnFinished(event -> {
                    animation_count--;
                    if(notEnded)
                        isGameOver();
                });
                if(notEnded)
                    parallelTransition.play();
                animation_count++;
                animation_frequency++;
            }
            matrix[i][j].increaseMass();
            if(matrix[i][j].getMass()==2 && !isCorner(i, j)) {
                makeCircle(matrix[i][j].two, i, j, color);
                TranslateTransition left=new TranslateTransition(Duration.millis(500), matrix[i][j]);
                TranslateTransition right=new TranslateTransition(Duration.millis(500), matrix[i][j].two);
                left.setByX(-7.5);
                right.setByX(7.5);
                ParallelTransition parallelTransition=new ParallelTransition(left, right);
                parallelTransition.play();
                rotateTwo(i, j, matrix);
                if(notEnded)
                    isGameOver();
            }
            else if(matrix[i][j].getMass()==3 && !isExtremeSide(i, j)) {
                makeCircle(matrix[i][j].three, i, j, color);
                TranslateTransition down=new TranslateTransition(Duration.millis(500), matrix[i][j]);
                TranslateTransition down1=new TranslateTransition(Duration.millis(500), matrix[i][j].two);
                TranslateTransition up=new TranslateTransition(Duration.millis(500), matrix[i][j].three);
                down.setByY(-7.5);
                down1.setByY(-7.5);
                up.setByY(7.5);
                ParallelTransition parallelTransition=new ParallelTransition(down, up, down1);
                parallelTransition.play();
                rotateThree(i, j, matrix);
                if(notEnded)
                    isGameOver();
            }
        }
        else {
            matrix[i][j]=new Ball(color);
            makeCircle(matrix[i][j], i, j, color);
            // Fade in on add
            FadeTransition fadeTransition=new FadeTransition(Duration.millis(500));
            fadeTransition.setNode(matrix[i][j]);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.setOnFinished(event -> {
                animation_count--;
                if(notEnded)
                    isGameOver();
                flag=1; // For first move
            });
            if(notEnded)
                fadeTransition.play();
            animation_count++;
            animation_frequency++;

            if(isCorner(i, j))
                rotateCorner(i ,j, matrix);
        }
        checkMass(i, j);
    }

    /**
     * Checks mass of the nodes present at the cell specified by
     * the row index and the column index provided. If the mass
     * exceeds the limit of the cell, sends the cell to be burst.
     *
     * @param i is the row index of the cell
     * @param j is the column index of the cell
     */
    private void checkMass(int i, int j) {
        // Fade out on remove
        ScaleTransition transition1=new ScaleTransition(Duration.millis(150));
        transition1.setNode(matrix[i][j]);
        transition1.setToX(1.2);
        transition1.setToY(1.2);

        ScaleTransition transition2=new ScaleTransition(Duration.millis(150));
        transition2.setNode(matrix[i][j].two);
        transition2.setToX(1.2);
        transition2.setToY(1.2);

        ScaleTransition transition3=new ScaleTransition(Duration.millis(150));
        transition3.setNode(matrix[i][j].three);
        transition3.setToX(1.2);
        transition3.setToY(1.2);

        ParallelTransition parallelTransition=new ParallelTransition(transition1, transition2, transition3);

        Circle temp1=matrix[i][j];
        Circle temp2=matrix[i][j].two;
        Circle temp3=matrix[i][j].three;
        Pane temp4=matrix[i][j].stackPane;

        if(i>0 && i<n-1 && j>0 && j<m-1) {
            if(getMass(i, j)==4){
                parallelTransition.setOnFinished(event -> {
                    grid.getChildren().removeAll(temp1, temp2, temp3, temp4);
                    animation_count--;
                    burst(4, i, j);
                });
                parallelTransition.play();
                animation_count++;
                animation_frequency++;
                matrix[i][j] = null;
            }
        }
        else if(isCorner(i, j)) {
            if(getMass(i, j)==2){
                parallelTransition.setOnFinished(event -> {
                    grid.getChildren().removeAll(temp1, temp2, temp3, temp4);
                    animation_count--;
                    burst(2, i, j);
                });
                parallelTransition.play();
                animation_count++;
                animation_frequency++;
                matrix[i][j] = null;
            }
        }
        else {
            if(getMass(i, j)==3){
                parallelTransition.setOnFinished(event -> {
                    grid.getChildren().removeAll(temp1, temp2, temp3, temp4);
                    animation_count--;
                    burst(3, i, j);
                });
                parallelTransition.play();
                animation_count++;
                animation_frequency++;
                matrix[i][j] = null;
            }
        }
    }

    /**
     * Bursts the nodes in the cell specified by the row index and
     * the column index provided.
     * Before that, it adds the splitting-animation to the nodes based
     * on the mass value and the position of the cell.
     *
     * @param val is the mass value of the node in the cell
     * @param i is the row index of the cell
     * @param j is the column index of the cell
     */
    private void burst(int val, int i, int j) {
        // Translate Animation
        // 1R, 2L, 3U, 4D
        Ball one=new Ball(color);
        one.setRadius(15);
        one.setFill(color);
        GridPane.setHalignment(one, HPos.CENTER);
        TranslateTransition right=new TranslateTransition(Duration.millis(250));
        right.setNode(one);
        right.setByX(25);

        Ball two=new Ball(color);
        two.setRadius(15);
        two.setFill(color);
        GridPane.setHalignment(two, HPos.CENTER);
        TranslateTransition left=new TranslateTransition(Duration.millis(250));
        left.setNode(two);
        left.setByX(-25);

        Ball three=new Ball(color);
        three.setFill(color);
        three.setRadius(15);
        GridPane.setHalignment(three, HPos.CENTER);
        TranslateTransition up=new TranslateTransition(Duration.millis(250));
        up.setNode(three);
        up.setByY(-25);

        Ball four=new Ball(color);
        four.setRadius(15);
        four.setFill(color);
        GridPane.setHalignment(four, HPos.CENTER);
        TranslateTransition down=new TranslateTransition(Duration.millis(250));
        down.setNode(four);
        down.setByY(25);

        up.setOnFinished(event -> setMass(i-1, j));
        right.setOnFinished(event -> setMass(i, j+1));
        left.setOnFinished(event -> setMass(i, j-1));
        down.setOnFinished(event -> setMass(i+1, j));

        ParallelTransition parallelTransition=new ParallelTransition(up, down, left, right);
        parallelTransition.setOnFinished(event -> {
            animation_count--;
            grid.getChildren().removeAll(one, two, three, four);
            if(notEnded)
                isGameOver();
        });

        if(val==4) {
            // Go UDLR
            grid.add(one, j, i);
            grid.add(two, j, i);
            grid.add(three, j, i);
            grid.add(four, j, i);
        }
        else if(val==3) {
            if(i==0) {
                // Go DLR
                grid.add(one, j, i);
                grid.add(two, j, i);
                grid.add(four, j, i);
            }
            else if(i==n-1) {
                // Go ULR
                grid.add(one, j, i);
                grid.add(two, j, i);
                grid.add(three, j, i);
            }
            else if(j==0) {
                // Go UDR
                grid.add(one, j, i);
                grid.add(three, j, i);
                grid.add(four, j, i);
            }
            else if(j==m-1) {
                // Go UDL
                grid.add(two, j, i);
                grid.add(three, j, i);
                grid.add(four, j, i);
            }
        }
        else if(val==2) {
            if(i==0 && j==0) {
                // Go RD
                grid.add(one, j, i);
                grid.add(four, j, i);
            }
            else if(i==0 && j==m-1) {
                // Go LD
                grid.add(two, j, i);
                grid.add(four, j, i);
            }
            else if(i==n-1 && j==0) {
                // Go UR
                grid.add(one, j, i);
                grid.add(three, j, i);
            }
            else if(i==n-1 && j==m-1) {
                // Go UL
                grid.add(two, j, i);
                grid.add(three, j, i);
            }
        }

        // Pop Sound on Burst
        if(soundMode==1 && notEnded) {
            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.stop());
        }

        if(notEnded)
            parallelTransition.play();
        animation_count++;
        animation_frequency++;
    }
}
