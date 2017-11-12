import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.Random;

class Player implements Serializable {
    private transient Color color;
    private double red, green, blue, opacity;
    protected int undo_left;

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

class Computer extends Player {
    Computer(Color color) {
        super(color);
        resetUndo();
    }

    @Override
    void resetUndo() {
        undo_left=Integer.MAX_VALUE;
    }

    @Override
    void takeTurn(Ball[][] matrix, int n, int m) {
        Random random=new Random();
        int i=random.nextInt(n);
        int j=random.nextInt(m);
        Grid grid=GamePage.getGrid();
        while(!grid.checkValidity(i, j)) {
            i=random.nextInt(n);
            j=random.nextInt(m);
        }
        grid.setPosition(i, j);
    }
}
