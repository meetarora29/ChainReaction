import javafx.scene.paint.Color;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Player class is used to portray the players who are playing the game.
 * It defines the color of the player and the number of undo moves the player
 * can perform.
 *
 * @author Meet Arora
 */
class Player implements Serializable {
    private transient Color color;
    private double red, green, blue, opacity;
    private int undo_left;
    private boolean takenTurn, hasLost;

    private static final long serialVersionUID = 3L;

    /**
     * Constructor based on the defined color.
     * The undo moves a player has at the beginning of the game is set as 3.
     *
     * @param color is the Color of the Player
     */
    Player(Color color) {
        this.color=color;
        undo_left=3;
        resolveColor();
        takenTurn=false;
        hasLost=false;
    }

    /**
     * Resets undo_left back to the initial value of 3.
     */
    void resetUndo() {
        undo_left=3;
    }

    /**
     * Gets the number of undo moves left for the player.
     *
     * @return undo_left which is the number of undo moves remaining
     */
    int getUndo() {
        return undo_left;
    }

    /**
     * Decrements the number of undo moves left whenever the player performs
     * an undo move.
     */
    void undo() {
        undo_left--;
    }

    /**
     * Makes the Color object for the player using the RGB and opacity
     * values stored.
     */
    void makeColor() {
        color=new Color(red, green, blue, opacity);
    }

    /**
     * Resolves the Color object into the RGB and opacity values for the
     * purpose of serializing.
     */
    private void resolveColor() {
        red=color.getRed();
        blue=color.getBlue();
        green=color.getGreen();
        opacity=color.getOpacity();
    }

    /**
     * Gets the color of the current player.
     *
     * @return the Color of the Player
     */
    Color getColor() {
        return color;
    }

    /**
     * An empty function definition for human players.
     * Required only by the Computer to play its moves.
     *
     * @param matrix is the grid of balls for the game
     * @param n is the number of rows
     * @param m is the number of columns
     */
    void takeTurn(Ball[][] matrix, int n, int m) {
        // Only for Computer
    }

    /**
     * Sets takenTurn to the given boolean value.
     *
     * @param b is the boolean value that is set to takenTurn
     */
    void setTakenTurn(boolean b) {
        takenTurn=b;
    }

    /**
     * Returns a boolean value signifying whether the player has taken a
     * turn yet.
     *
     * @return true is the player has taken a turn and false otherwise
     */
    boolean hasTakenTurn() {
        return takenTurn;
    }

    /**
     * Sets hasLost to the given boolean value.
     *
     * @param b is the boolean value that is set to hasLost
     */
    void setHasLost(boolean b) {
        hasLost=b;
    }

    /**
     * Returns a boolean value signifying whether the player has lost already.
     *
     * @return true if the player has lost and false otherwise
     */
    boolean hasLost() {
        return hasLost;
    }
}

/**
 * Computer class is used to make a simple AI opponent for single-
 * player mode.
 * @author Meet Arora
 */
class Computer extends Player implements Serializable {
    
    private static final long serialVersionUID = 4L;

    /**
     * Constructor based on the defined color.
     *
     * @param color is the Color for the balls of the Computer
     */
    Computer(Color color) {
        super(color);
    }

    /**
     * Checks if the ball at the given row index and column index is
     * burst-ready or not.
     *
     * @param matrix is the grid of balls for the game
     * @param i is the row index
     * @param j is the column index
     * @param grid is the Grid object of the game page
     * @return true if the ball is burstable and false if not
     */
    private boolean isBurstable(Ball[][] matrix, int i, int j, Grid grid) {
        if(matrix[i][j].getMass()==3)
            return true;
        if(matrix[i][j].getMass()==2 && grid.isExtremeSide(i, j))
            return true;
        if(matrix[i][j].getMass()==1 && grid.isCorner(i, j))
            return true;
        return false;
    }

    /**
     * A function that automates a move for the Computer based on probability
     * using randomisation. The probability are as follows:
     * 55% probability to burst some ball
     * 30% probability to select some existing ball
     * 10% probability to select any of the valid points
     *
     * @param matrix is the grid of balls for the game
     * @param n      is the number of rows
     * @param m      is the number of columns
     */
    @Override
    void takeTurn(Ball[][] matrix, int n, int m) {
        Random random=new Random();
        Grid grid=GamePage.getGrid();
        ArrayList<Point> burst_points=new ArrayList<>();
        ArrayList<Point> points=new ArrayList<>();

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(matrix[i][j]!=null && matrix[i][j].getColor().equals(getColor())) {
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
