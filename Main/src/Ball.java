import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.Serializable;

class SerializableBall implements Serializable {
    private int mass;
    double red, green, blue, opacity;

    private static final long serialVersionUID = 2L;

    SerializableBall(Ball b) {
        mass=b.getMass();
        red=b.red;
        blue=b.blue;
        green=b.green;
        opacity=b.opacity;
    }

    int getMass() {
        return mass;
    }
}

class Ball extends Circle {
    private int mass;
    private transient Color color;
    double red, green, blue, opacity;

    Ball(Ball b) {
        mass=b.getMass();
        color=b.getColor();
        resolveColor();
    }

    Ball(Color c) {
        color=c;
        resolveColor();
        mass=1;
    }

    Ball(SerializableBall sb) {
        mass=sb.getMass();
        red=sb.red;
        blue=sb.blue;
        green=sb.green;
        opacity=sb.opacity;
        makeColor();
    }

    private void makeColor() {
        color=new Color(red, green, blue, opacity);
    }

    private void resolveColor() {
        red=color.getRed();
        blue=color.getBlue();
        green=color.getGreen();
        opacity=color.getOpacity();
    }

    int getMass() {
        return mass;
    }

    Color getColor() {
        return color;
    }

    void setColor(Color color) {
        this.color = color;
        resolveColor();
    }

    void increaseMass() {
        mass++;
    }
}
