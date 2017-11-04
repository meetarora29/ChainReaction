import javafx.scene.paint.Color;

import java.io.Serializable;

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
}
