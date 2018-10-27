package name.saifmahmud.cse423.circle;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import name.saifmahmud.cse423.Point;

import java.awt.*;

public class Midpoint extends AbstractAlgorithm {
    public Midpoint(Dimension dimension) {
        super(dimension);
    }

    @Override
    public void draw(GLAutoDrawable drawable, Point center, double radius) {
        double d = 1 - radius;
        double x = radius, y = 0;

        final GL2 gl = drawable.getGL().getGL2();
        gl.glPointSize(1);
        gl.glBegin(GL2.GL_POINTS);

        while (x >= y) {
            glDraw(gl, x, y, center.x, center.y);

            if (d <= 0) {
                d = d + (2 * y) + 3;
                y++;
            } else {
                d = d - 2 * x + 2 * y + 5;
                x--; y++;
            }
        }

        gl.glEnd();
    }

    private void glDraw(GL2 gl, double x, double y, double cx, double cy) {
        gl.glVertex2d(2 * (x + cx) / dimension.width, 2 * (y + cy) / dimension.height);
        gl.glVertex2d(2 * (y + cx) / dimension.width, 2 * (x + cy) / dimension.height);
        gl.glVertex2d(2 * - (y - cx) / dimension.width, 2 * (x + cy) / dimension.height);
        gl.glVertex2d(2 * - (x - cx) / dimension.width, 2 * (y + cy) / dimension.height);

        gl.glVertex2d(2 * - (x - cx) / dimension.width, 2 * - (y - cy) / dimension.height);
        gl.glVertex2d(2 * - (y - cx) / dimension.width, 2 * - (x - cy) / dimension.height);
        gl.glVertex2d(2 * (y + cx) / dimension.width, 2 * - (x - cy) / dimension.height);
        gl.glVertex2d(2 * (x + cx) / dimension.width, 2 * - (y - cy) / dimension.height);
    }
}
