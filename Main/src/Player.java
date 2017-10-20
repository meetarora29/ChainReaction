import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Player {
    Color color;
    static Grid grid;

    Player(Color color) {
        this.color=color;
    }

    void takeTurn(int i, int j) {
        grid.setPosition(i, j, this);
    }

    Color getColor() {
        return color;
    }

    static void undo() {
        grid.undo();
    }

    static void setGrid(int n, int m, GridPane g) {
        grid=new Grid(n, m, g);
    }
}
