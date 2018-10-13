package name.saifmahmud.cse423.lab2;

import com.jogamp.opengl.GLAutoDrawable;
import name.saifmahmud.cse423.AbstractAssignment;
import name.saifmahmud.cse423.Point;
import name.saifmahmud.cse423.line.Dda;

import javax.swing.*;
import java.util.ArrayList;

public class Triangle extends AbstractAssignment {
    private Dda dda;

    public static void main(String[] args) {
        Triangle triangle = new Triangle();
        canvas.addGLEventListener(triangle);
        canvas.setSize(500, 500);

        JFrame frame = new JFrame("Triangle Lab");

        frame.getContentPane().add(canvas);
        frame.setSize(canvas.getPreferredSize());
        frame.setVisible(true);

        triangle.dda = new Dda(canvas.getPreferredSize());
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        drawZoneLines(drawable);

        ArrayList<Point> points = new ArrayList<>();
        for (double i = 90; i < 450; i += 120) {
            points.add(Point.fromPolar(200, i));
        }

        for (Point start : points) {
            for (Point end : points) {
                if (start.equals(end)) {
                    continue;
                }

                dda.draw(drawable, start, end);
            }
        }
    }
}
