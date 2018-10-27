package name.saifmahmud.cse423.line;

import com.jogamp.opengl.GLAutoDrawable;
import name.saifmahmud.cse423.Point;

import java.awt.*;

public abstract class AbstractAlgorithm extends name.saifmahmud.cse423.AbstractAlgorithm {
    public AbstractAlgorithm(Dimension dimension) {
        super(dimension);
    }

    abstract public void draw(GLAutoDrawable drawable, Point start, Point end);
}
