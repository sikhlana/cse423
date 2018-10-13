package name.saifmahmud.cse423;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

abstract public class AbstractAssignment implements GLEventListener {
    protected static GLProfile profile = GLProfile.get(GLProfile.GL2);
    protected static GLCapabilities capabilities = new GLCapabilities(profile);
    protected static GLCanvas canvas = new GLCanvas(capabilities);

    protected void drawZoneLines(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glPointSize(1);
        gl.glBegin(GL2.GL_POINTS);

        for (double i = 0; i <= 1; i += 0.1) {
            gl.glVertex2d(i, 0);
            gl.glVertex2d(0, i);
            gl.glVertex2d(-i, 0);
            gl.glVertex2d(0, -i);
            gl.glVertex2d(i, i);
            gl.glVertex2d(-i, i);
            gl.glVertex2d(i, -i);
            gl.glVertex2d(-i, -i);
        }

        gl.glEnd();
    }

    @Override
    public void init(GLAutoDrawable drawable) {

    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int i, int i1, int i2, int i3) {

    }
}
