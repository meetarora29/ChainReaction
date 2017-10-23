import javafx.animation.*;
import javafx.geometry.HPos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Grid {
    private int n, m;
    private Ball[][] matrix, prev_state;
    private Color color;
    private GridPane grid;
    private Player[] players;
    private int curr_player, numPlayers;
    private int flag, animation_count;

    Grid(int n, int m, GridPane grid, Player[] players) {
        this.n=n;
        this.m=m;
        this.matrix=new Ball[n][m];
        this.grid=grid;
        this.players=players;
        numPlayers=players.length;
    }

    void undo() {
        matrix=prev_state;
        prevPlayer();
    }

    private void saveState() {
        prev_state=new Ball[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(matrix[i][j]!=null)
                    prev_state[i][j]=new Ball(matrix[i][j]);
            }
        }
    }

    private void prevPlayer() {
        if(curr_player>0)
            curr_player--;
        else
            curr_player=numPlayers-1;
    }

    private void nextPlayer() {
        curr_player=(curr_player+1)%numPlayers;
    }

    private boolean checkValidity(int i, int j) {
        return matrix[i][j] == null || color == matrix[i][j].color;
    }

    private boolean checkWin() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(matrix[i][j]!=null && matrix[i][j].color!=color)
                    return false;
            }
        }
        return true;
    }

    boolean noAnimation() { return animation_count==0; }

    private void isGameOver() {
        if(checkWin() && flag!=0 && noAnimation())
            System.out.println("Game Over!!!");
    }

    void setPosition(int i, int j) {
        saveState();
        color=players[curr_player].getColor();
        // TODO: Change grid line color
        if(!checkValidity(i, j))
            return;
        setMass(i, j);
        nextPlayer();
    }

    private int getMass(int i, int j) {
        if(matrix[i][j]==null)
            matrix[i][j]=new Ball(color);
        return matrix[i][j].mass;
    }

    private void setMass(int i, int j) {
        // Check Invalid Index
        if(i<0 || i>n-1 || j<0 || j>m-1)
            return;

        if(matrix[i][j]!=null) {
            if (matrix[i][j].color != color) {
                // Changing Color
                matrix[i][j].color = color;
                FillTransition fillTransition=new FillTransition(Duration.millis(1000));
                fillTransition.setShape(matrix[i][j]);
                fillTransition.setToValue(color);
                fillTransition.setOnFinished(event -> {
                    animation_count--;
                    isGameOver();
                });
                fillTransition.play();
                animation_count++;
            }
            matrix[i][j].increaseMass();
            if(matrix[i][j].getMass()==2)
                matrix[i][j].setRadius(20);
            else if(matrix[i][j].getMass()==3)
                matrix[i][j].setRadius(25);
        }
        else {
            matrix[i][j]=new Ball(color);
            matrix[i][j].setRadius(15);
            matrix[i][j].setFill(color);
            grid.add(matrix[i][j], j, i);
            // Fade in on add
            FadeTransition fadeTransition=new FadeTransition(Duration.millis(1000));
            fadeTransition.setNode(matrix[i][j]);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.setOnFinished(event -> {
                animation_count--;
                isGameOver();
                flag=1; // For first move
            });
            fadeTransition.play();
            animation_count++;
            GridPane.setHalignment(matrix[i][j], HPos.CENTER);
            matrix[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                if(noAnimation())
                    setPosition(i, j);
            });
        }
        checkMass(i, j);
    }

    private void checkMass(int i, int j) {
        // Fade out on remove
        FadeTransition fadeTransition=new FadeTransition(Duration.millis(1000));
        fadeTransition.setNode(matrix[i][j]);
        fadeTransition.setToValue(0);

        if(i>0 && i<n-1 && j>0 && j<m-1) {
            if(getMass(i, j)==4){
                fadeTransition.setOnFinished(event -> {
                    grid.getChildren().remove(matrix[i][j]);
                    animation_count--;
                    burst(4, i, j);
                });
                fadeTransition.play();
                animation_count++;
                matrix[i][j] = null;
            }
        }
        else if((i==0 && (j==0 || j==m-1)) || (i==n-1 && (j==0 || j==m-1))) {
            if(getMass(i, j)==2){
                fadeTransition.setOnFinished(event -> {
                    grid.getChildren().remove(matrix[i][j]);
                    animation_count--;
                    burst(2, i, j);
                });
                fadeTransition.play();
                animation_count++;
                matrix[i][j] = null;
            }
        }
        else {
            if(getMass(i, j)==3){
                fadeTransition.setOnFinished(event -> {
                    grid.getChildren().remove(matrix[i][j]);
                    animation_count--;
                    burst(3, i, j);
                });
                fadeTransition.play();
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

        parallelTransition.play();
        animation_count++;
    }
}
