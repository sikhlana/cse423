package name.saifmahmud.cse423;

import com.jogamp.opengl.GLAutoDrawable;

import java.awt.*;

abstract public class AbstractAlgorithm {
    protected final Dimension dimension;

    public AbstractAlgorithm(Dimension dimension) {
        this.dimension = dimension;
    }

    abstract public void draw(GLAutoDrawable drawable, Point start, Point end);
}
