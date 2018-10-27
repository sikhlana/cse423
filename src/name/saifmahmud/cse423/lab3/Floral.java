package name.saifmahmud.cse423.lab3;

import com.jogamp.opengl.GLAutoDrawable;
import name.saifmahmud.cse423.AbstractAssignment;
import name.saifmahmud.cse423.Point;
import name.saifmahmud.cse423.circle.Midpoint;

import javax.swing.*;

public class Floral extends AbstractAssignment {
    public static void main(String[] args) {
        Floral floral = new Floral();
        canvas.addGLEventListener(floral);
        canvas.setSize(500, 500);

        JFrame frame = new JFrame("Floral Design");

        frame.getContentPane().add(canvas);
        frame.setSize(canvas.getPreferredSize());
        frame.setVisible(true);

        floral.algo = new Midpoint(canvas.getPreferredSize());
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        drawZoneLines(drawable);

        double radius = 200;
        Point center = new Point(0, 0);

        ((Midpoint) algo).draw(drawable, center, radius);
        radius /= 2;

        for (double angle = 0; angle < 360; angle += 45) {
            center = Point.fromPolar(radius, angle);
            ((Midpoint) algo).draw(drawable, center, radius);
        }
    }
}
