import javafx.scene.paint.Color;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

class Player implements Serializable {
    private transient Color color;
    private double red, green, blue, opacity;
    private int undo_left;

    private static final long serialVersionUID = 3L;

    Player(Color color) {
        this.color=color;
        undo_left=3;
        resolveColor();
    }

    void resetUndo() {
        undo_left=3;
    }

    int getUndo() {
        return undo_left;
    }

    void undo() {
        undo_left--;
    }

    void makeColor() {
        color=new Color(red, green, blue, opacity);
    }

    private void resolveColor() {
        red=color.getRed();
        blue=color.getBlue();
        green=color.getGreen();
        opacity=color.getOpacity();
    }

    Color getColor() {
        return color;
    }

    void takeTurn(Ball[][] matrix, int n, int m) {
        // Only for Computer
    }
}

class Computer extends Player implements Serializable {
    
    private static final long serialVersionUID = 4L;

    Computer(Color color) {
        super(color);
    }

    private boolean isBurstable(Ball[][] matrix, int i, int j, Grid grid) {
        if(matrix[i][j].getMass()==3)
            return true;
        if(matrix[i][j].getMass()==2 && grid.isExtremeSide(i, j))
            return true;
        if(matrix[i][j].getMass()==1 && grid.isCorner(i, j))
            return true;
        return false;
    }

    @Override
    void takeTurn(Ball[][] matrix, int n, int m) {
        Random random=new Random();
        Grid grid=GamePage.getGrid();
        ArrayList<Point> burst_points=new ArrayList<>();
        ArrayList<Point> points=new ArrayList<>();

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(matrix[i][j]!=null && matrix[i][j].getColor()==getColor()) {
                    if (isBurstable(matrix, i, j, grid))
                        burst_points.add(new Point(i, j));
                    else
                        points.add(new Point(i, j));
                }
            }
        }

        int i, j;
        int r=random.nextInt(100);

        // 55% chance to burst some ball
        if(r<55 && !burst_points.isEmpty()) {
            int temp=random.nextInt(burst_points.size());
            i=burst_points.get(temp).x;
            j=burst_points.get(temp).y;
        }
        // 30% chance to select some existing ball
        else if(r<85 && !points.isEmpty()) {
            int temp=random.nextInt(points.size());
            i=points.get(temp).x;
            j=points.get(temp).y;
        }
        // 15% chance to select any valid point
        else {
            i=random.nextInt(n);
            j=random.nextInt(m);
            while (!grid.checkValidity(i, j)) {
                i = random.nextInt(n);
                j = random.nextInt(m);
            }
        }
        // To show which cell was chosen by the computer
        GamePage.setFocus(i, j);
        grid.setPosition(i, j);
    }
}
