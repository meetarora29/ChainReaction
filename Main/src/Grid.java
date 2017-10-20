import javafx.geometry.HPos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Grid {
    private int n, m;
    private Ball[][] matrix, prev_state;
    private Color color;
    private GridPane grid;
    private Player[] players;
    private int curr_player, numPlayers;
    static private int count;

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

    void setPosition(int i, int j) {
        saveState();
        color=players[curr_player].getColor();
        // TODO: Change grid line color
        if(!checkValidity(i, j))
            return;
        setMass(i, j);
        if(checkWin() && count++!=0)
            System.out.println("Game Over!!!");
        nextPlayer();
    }

    private int getMass(int i, int j) {
        if(matrix[i][j]==null)
            matrix[i][j]=new Ball(color);
        return matrix[i][j].mass;
    }

    private void setMass(int i, int j) {
        if(matrix[i][j]!=null) {
            if (matrix[i][j].color != color) {
                matrix[i][j].color = color;
                matrix[i][j].setFill(color);
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
            GridPane.setHalignment(matrix[i][j], HPos.CENTER);
            matrix[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                setPosition(i, j);
            });
        }
        checkMass(i, j);
    }

    private void checkMass(int i, int j) {
        if(i>0 && i<n-1 && j>0 && j<m-1) {
            if(getMass(i, j)==4)
                burst(4, i, j);
        }
        else if((i==0 && (j==0 || j==m-1)) || (i==n-1 && (j==0 || j==m-1))) {
            if(getMass(i, j)==2)
                burst(2, i ,j);
        }
        else {
            if(getMass(i, j)==3)
                burst(3, i, j);
        }
    }

    private void burst(int val, int i, int j) {
        if(val>1 && val<5) {
            grid.getChildren().remove(matrix[i][j]);
            matrix[i][j] = null;
        }
        if(val==4) {
            setMass(i-1, j);
            setMass(i, j+1);
            setMass(i, j-1);
            setMass(i+1, j);
        }
        else if(val==3) {
            if(i==0) {
                setMass(i, j+1);
                setMass(i, j-1);
                setMass(i+1, j);
            }
            else if(i==n-1) {
                setMass(i, j+1);
                setMass(i, j-1);
                setMass(i-1, j);
            }
            else if(j==0) {
                setMass(i, j+1);
                setMass(i-1, j);
                setMass(i+1, j);
            }
            else if(j==m-1) {
                setMass(i-1, j);
                setMass(i+1, j);
                setMass(i, j-1);
            }
        }
        else if(val==2) {
            if(i==0 && j==0) {
                setMass(i+1, j);
                setMass(i, j+1);
            }
            else if(i==0 && j==m-1) {
                setMass(i, j-1);
                setMass(i+1, j);
            }
            else if(i==n-1 && j==0) {
                setMass(i, j+1);
                setMass(i-1, j);
            }
            else if(i==n-1 && j==m-1) {
                setMass(i, j-1);
                setMass(i-1, j);
            }
        }
    }
}
