import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Grid {
    int n, m;
    Ball[][] matrix, prev_state;
    Color color;
    GridPane grid;
    Player current;

    Grid(int n, int m, GridPane grid) {
        this.n=n;
        this.m=m;
        this.matrix=new Ball[n][m];
        this.grid=grid;
    }

    void undo() {
        matrix=prev_state;
    }

    void saveState() {
        prev_state=new Ball[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(matrix[i][j]!=null)
                    prev_state[i][j]=new Ball(matrix[i][j]);
            }
        }
    }

    void setPosition(int i, int j, Player current) {
        saveState();
        this.current=current;
        color=current.getColor();
        setMass(i, j);
    }

    int getMass(int i , int j) {
        if(matrix[i][j]==null)
            matrix[i][j]=new Ball(color);
        return matrix[i][j].mass;
    }

    private void setMass(int i, int j) {
        if(matrix[i][j]!=null) {
            if(matrix[i][j].color!=color)
                matrix[i][j].color=color;
            matrix[i][j].mass++;
        }
        else {
            matrix[i][j]=new Ball(color);
            matrix[i][j].setRadius(25);
            grid.add(matrix[i][j], j, i);
            matrix[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                System.out.println(matrix[i][j].mass);
                setPosition(i, j, current);
            });
        }
        checkMass(i, j);
    }

    void checkMass(int i, int j) {
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

    void burst(int val, int i, int j) {
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
