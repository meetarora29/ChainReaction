import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.Serializable;

class Player implements Serializable {
    private transient Color color;
    private static Grid grid;

    Player(Color color) {
        this.color=color;
    }

    void takeTurn(int i, int j) {
        grid.setPosition(i, j);
    }

    Color getColor() {
        return color;
    }

    static void undo() {
        grid.undo();
    }

    static Grid setGrid(int n, int m, GridPane g, Player[] players) {
        return grid=new Grid(n, m, g, players);
    }
}
