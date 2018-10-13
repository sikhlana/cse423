package name.saifmahmud.cse423.line;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import name.saifmahmud.cse423.AbstractAlgorithm;
import name.saifmahmud.cse423.Point;

import java.awt.*;

public class Dda extends AbstractAlgorithm {
    public Dda(Dimension dimension) {
        super(dimension);
    }

    public void draw(GLAutoDrawable drawable, Point start, Point end) {
        double dx = end.x - start.x;
        double dy = end.y - start.y;

        double steps = Math.abs(dx) > Math.abs(dy) ? Math.abs(dx) : Math.abs(dy);

        double xIncrement = dx / steps;
        double yIncrement = dy / steps;

        double x = start.x, y = start.y;

        final GL2 gl = drawable.getGL().getGL2();
        gl.glPointSize(1);
        gl.glBegin(GL2.GL_POINTS);

        for (int i = 0; i <= steps; i++) {
            gl.glVertex2d(2 * x / dimension.width, 2 * y / dimension.height);

            x += xIncrement;
            y += yIncrement;
        }

        gl.glEnd();
    }
}
