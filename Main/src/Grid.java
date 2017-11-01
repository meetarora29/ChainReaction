import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Grid implements Serializable {
    private int n, m;
    private transient Ball[][] matrix, prev_state;
    private SerializableBall[][] s_matrix;
    private transient Color color;
    private transient GridPane grid;
    private Player[] players;
    static Stage stage;
    private int curr_player, numPlayers, flag, animation_count;
    private transient myStack<Ball[][]> moveStack;
    private int load, count;

    private static final long serialVersionUID = 1L;

    Grid(int n, int m, GridPane grid, Player[] players) {
        this.n=n;
        this.m=m;
        matrix=new Ball[n][m];
        s_matrix=new SerializableBall[n][m];
        this.grid=grid;
        this.players=players;
        numPlayers=players.length;
        moveStack=new myStack<>(3*numPlayers);
        count=0;
    }

    void serializeMatrix() {
        for(int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j]!=null)
                    s_matrix[i][j] = new SerializableBall(matrix[i][j]);
            }
        }
        load=0;
    }

    private void makeNode(int i, int j, Ball[][] matrix) {
        if(matrix[i][j]!=null) {
            if(matrix[i][j].getMass()==1)
                matrix[i][j].setRadius(15);
            else if(matrix[i][j].getMass()==2)
                matrix[i][j].setRadius(20);
            else if(matrix[i][j].getMass()==3)
                matrix[i][j].setRadius(25);
            matrix[i][j].setFill(matrix[i][j].getColor());
            grid.add(matrix[i][j], j, i);
            GridPane.setHalignment(matrix[i][j], HPos.CENTER);
            matrix[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                if(noAnimation())
                    setPosition(i, j);
            });
        }
    }

    GridPane resolve(GridPane grid) {
        // Transient Initialisations
        matrix=new Ball[n][m];
        this.grid=grid;
        moveStack=new myStack<>(3*numPlayers);
        load=1;

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

        return grid;
    }

    private void removeGridNodes() {
        ArrayList<Node> list=new ArrayList<>();
        for(Node node : grid.getChildren()) {
            if(node instanceof Ball)
                list.add(node);
        }
        grid.getChildren().removeAll(list);
    }

    void restartGame() {
        removeGridNodes();
        matrix=new Ball[n][m];
        flag=0;
        animation_count=0;
        curr_player=0;
        moveStack=new myStack<>(3*numPlayers);
        count=0;
    }

    void undo() {
        if(moveStack.isEmpty())
            return;

        if(moveStack.size()==1 && load==0 && count<=1) {
            restartGame();
            return;
        }

        prev_state=moveStack.pop();
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                grid.getChildren().removeAll(matrix[i][j]);
                makeNode(i, j, prev_state);
            }
        }

        matrix=prev_state;
        prevPlayer();
        count--;
    }

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

    private void prevPlayer() {
        if(curr_player>0)
            curr_player--;
        else
            curr_player=numPlayers-1;
        GamePage.changeGridLineColor(players[curr_player].getColor());
    }

    private void nextPlayer() {
        curr_player=(curr_player+1)%numPlayers;
        GamePage.changeGridLineColor(players[curr_player].getColor());
    }

    private boolean checkValidity(int i, int j) {
        return matrix[i][j] == null || color.equals(matrix[i][j].getColor());
    }

    private boolean checkWin() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(matrix[i][j]!=null && matrix[i][j].getColor()!=color)
                    return false;
            }
        }
        return true;
    }

    boolean noAnimation() { return animation_count==0; }

    private void isGameOver() throws IOException {
        if(checkWin() && flag!=0 && noAnimation()) {
            System.out.println("Game Over!!!");
            stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Congratulations!!");
            AnchorPane pane=FXMLLoader.load(getClass().getResource("fxml_files/game_end.fxml"));
            stage.setScene(new Scene(pane));
            stage.show();
        }
    }

    void setPosition(int i, int j) {
        color=players[curr_player].getColor();
        if(!checkValidity(i, j))
            return;
        saveState();
        setMass(i, j);
        count++;
        nextPlayer();
    }

    private int getMass(int i, int j) {
        if(matrix[i][j]==null)
            matrix[i][j]=new Ball(color);
        return matrix[i][j].getMass();
    }

    private void makeCircle(Circle s, int i, int j) {
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

    private boolean isCorner(int i, int j) {
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

    private boolean isExtremeSide(int i, int j) {
        if(i==0 || i==n-1 || j==0 || j==m-1)
            return true;
        return false;
    }

    private void setMass(int i, int j) {
        // Check Invalid Index
        if(i<0 || i>n-1 || j<0 || j>m-1)
            return;

        if(matrix[i][j]!=null) {
            if (matrix[i][j].getColor() != color) {
                // Changing Color
                matrix[i][j].setColor(color);
                FillTransition fillTransition1=new FillTransition(Duration.millis(1000));
                fillTransition1.setShape(matrix[i][j]);
                fillTransition1.setToValue(color);

                FillTransition fillTransition2=new FillTransition(Duration.millis(1000));
                fillTransition2.setShape(matrix[i][j].two);
                fillTransition2.setToValue(color);

                FillTransition fillTransition3=new FillTransition(Duration.millis(1000));
                fillTransition3.setShape(matrix[i][j].three);
                fillTransition3.setToValue(color);

                ParallelTransition parallelTransition=new ParallelTransition(fillTransition1, fillTransition2, fillTransition3);

                parallelTransition.setOnFinished(event -> {
                    animation_count--;
                    try {
                        isGameOver();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                parallelTransition.play();
                animation_count++;
            }
            matrix[i][j].increaseMass();
            if(matrix[i][j].getMass()==2 && !isCorner(i, j)) {
                makeCircle(matrix[i][j].two, i, j);
                matrix[i][j].setTranslateX(-7.5);
                matrix[i][j].two.setTranslateX(7.5);
            }
            else if(matrix[i][j].getMass()==3 && !isExtremeSide(i, j)) {
                makeCircle(matrix[i][j].three, i, j);
                matrix[i][j].setTranslateY(-7.5);
                matrix[i][j].two.setTranslateY(-7.5);
                matrix[i][j].three.setTranslateY(7.5);
            }
        }
        else {
            matrix[i][j]=new Ball(color);
            makeCircle(matrix[i][j], i, j);
            // Fade in on add
            FadeTransition fadeTransition=new FadeTransition(Duration.millis(1000));
            fadeTransition.setNode(matrix[i][j]);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.setOnFinished(event -> {
                animation_count--;
                try {
                    isGameOver();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                flag=1; // For first move
            });
            fadeTransition.play();
            animation_count++;
        }
        checkMass(i, j);
    }

    private void checkMass(int i, int j) {
        // Fade out on remove
        ScaleTransition transition1=new ScaleTransition(Duration.millis(500));
        transition1.setNode(matrix[i][j]);
        transition1.setToX(1.2);
        transition1.setToY(1.2);

        ScaleTransition transition2=new ScaleTransition(Duration.millis(500));
        transition2.setNode(matrix[i][j].two);
        transition2.setToX(1.2);
        transition2.setToY(1.2);

        ScaleTransition transition3=new ScaleTransition(Duration.millis(500));
        transition3.setNode(matrix[i][j].three);
        transition3.setToX(1.2);
        transition3.setToY(1.2);

        ParallelTransition parallelTransition=new ParallelTransition(transition1, transition2, transition3);

        Circle temp1=matrix[i][j];
        Circle temp2=matrix[i][j].two;
        Circle temp3=matrix[i][j].three;

        if(i>0 && i<n-1 && j>0 && j<m-1) {
            if(getMass(i, j)==4){
                parallelTransition.setOnFinished(event -> {
                    grid.getChildren().removeAll(temp1, temp2, temp3);
                    animation_count--;
                    burst(4, i, j);
                });
                parallelTransition.play();
                animation_count++;
                matrix[i][j] = null;
            }
        }
        else if(isCorner(i, j)) {
            if(getMass(i, j)==2){
                parallelTransition.setOnFinished(event -> {
                    grid.getChildren().removeAll(temp1, temp2, temp3);
                    animation_count--;
                    burst(2, i, j);
                });
                parallelTransition.play();
                animation_count++;
                matrix[i][j] = null;
            }
        }
        else {
            if(getMass(i, j)==3){
                parallelTransition.setOnFinished(event -> {
                    grid.getChildren().removeAll(temp1, temp2, temp3);
                    animation_count--;
                    burst(3, i, j);
                });
                parallelTransition.play();
                animation_count++;
                matrix[i][j] = null;
            }
        }
    }

    private void burst(int val, int i, int j) {
        // Translate Animation
        // 1R, 2L, 3U, 4D
        Ball one=new Ball(color);
        one.setRadius(15);
        one.setFill(color);
        GridPane.setHalignment(one, HPos.CENTER);
        TranslateTransition right=new TranslateTransition(Duration.millis(500));
        right.setNode(one);
        right.setByX(25);

        Ball two=new Ball(color);
        two.setRadius(15);
        two.setFill(color);
        GridPane.setHalignment(two, HPos.CENTER);
        TranslateTransition left=new TranslateTransition(Duration.millis(500));
        left.setNode(two);
        left.setByX(-25);

        Ball three=new Ball(color);
        three.setFill(color);
        three.setRadius(15);
        GridPane.setHalignment(three, HPos.CENTER);
        TranslateTransition up=new TranslateTransition(Duration.millis(500));
        up.setNode(three);
        up.setByY(-25);

        Ball four=new Ball(color);
        four.setRadius(15);
        four.setFill(color);
        GridPane.setHalignment(four, HPos.CENTER);
        TranslateTransition down=new TranslateTransition(Duration.millis(500));
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
            try {
                isGameOver();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

        parallelTransition.play();
        animation_count++;
    }
}
