import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.Serializable;

/**
 * A Serializable version of the Ball class.
 * Used only when saving or resuming a game.
 *
 * @author Meet Arora
 */
class SerializableBall implements Serializable {
    private int mass;
    double red, green, blue, opacity;

    private static final long serialVersionUID = 2L;

    /**
     * Constructor that make a serializable copy of a Ball object
     *
     * @param b is the Ball object that is to be duplicated and made serializable
     */
    SerializableBall(Ball b) {
        mass=b.getMass();
        red=b.red;
        blue=b.blue;
        green=b.green;
        opacity=b.opacity;
    }

    /**
     * Gets the mass of the object
     *
     * @return mass of the SerializableBall object
     */
    int getMass() {
        return mass;
    }
}

/**
 * Ball class is used to display circles on the grid for the game.
 * It inherits from Circle which is non-serializable class. Hence, the need
 * for the SerializableBall class.
 *
 * @author Meet Arora
 */
class Ball extends Circle {
    private int mass;
    private transient Color color;
    double red, green, blue, opacity;
    Circle two, three;
    Pane stackPane;

    {
        two=new Circle();
        three=new Circle();
        stackPane=new StackPane();
    }

    /**
     * Constructor that builds a new Ball object from an existing
     * Ball object.
     *
     * @param b is the Ball object that needs to be duplicated
     */
    Ball(Ball b) {
        mass=b.getMass();
        color=b.getColor();
        resolveColor();
    }

    /**
     * Constructor that creates a Ball object using the given color.
     *
     * @param c is the Color used for the ball
     */
    Ball(Color c) {
        color=c;
        resolveColor();
        mass=1;
    }

    /**
     * Constructor that creates a Ball object using a SerializableBall object.
     * This is used during deserialize to get back the Ball objects
     * from the SerializableBall objects.
     *
     * @param sb is the SerializableBall object which is resolved into a Ball object
     */
    Ball(SerializableBall sb) {
        mass=sb.getMass();
        red=sb.red;
        blue=sb.blue;
        green=sb.green;
        opacity=sb.opacity;
        makeColor();
    }

    /**
     * Creates the color using the RGB and opacity values.
     */
    private void makeColor() {
        color=new Color(red, green, blue, opacity);
    }

    /**
     * Resolves the color into the RGB and opacity values.
     */
    private void resolveColor() {
        red=color.getRed();
        blue=color.getBlue();
        green=color.getGreen();
        opacity=color.getOpacity();
    }

    /**
     * Gets the mass of the Ball object.
     *
     * @return the mass of the object
     */
    int getMass() {
        return mass;
    }

    /**
     * Gets the color of the Ball object.
     *
     * @return the Color of the Ball object
     */
    Color getColor() {
        return color;
    }

    /**
     * Sets the color for the Ball object.
     *
     * @param color is the Color value for the Ball object
     */
    void setColor(Color color) {
        this.color = color;
        resolveColor();
    }

    /**
     * Increments mass whenever a move is made on an existing cell.
     */
    void increaseMass() {
        mass++;
    }
}
