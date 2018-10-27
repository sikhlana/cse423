package name.saifmahmud.cse423.clipping;

import com.jogamp.opengl.GLAutoDrawable;
import name.saifmahmud.cse423.Point;
import name.saifmahmud.cse423.line.Dda;

import java.awt.*;

public abstract class AbstractAlgorithm extends name.saifmahmud.cse423.AbstractAlgorithm {
    protected Dimension canvas;
    protected Dda dda;

    protected final double xmax, xmin, ymax, ymin;

    abstract public void clip(GLAutoDrawable drawable, Point start, Point end);

    public AbstractAlgorithm(Dimension dimension, Dimension canvas) {
        super(dimension);
        dda = new Dda(dimension);
        this.canvas = canvas;

        xmax = (double) canvas.width / 2;
        ymax = (double) canvas.height / 2;
        xmin = - xmax;
        ymin = - ymax;
    }

    public void drawCanvas(GLAutoDrawable drawable) {
        Point topLeft = new Point(xmin, ymax);
        Point topRight = new Point(xmax, ymax);
        Point bottomLeft = new Point(xmin, ymin);
        Point bottomRight = new Point(xmax, ymin);

        dda.draw(drawable, topLeft, topRight);
        dda.draw(drawable, topLeft, bottomLeft);
        dda.draw(drawable, bottomRight, bottomLeft);
        dda.draw(drawable, bottomRight, topRight);
    }
}
