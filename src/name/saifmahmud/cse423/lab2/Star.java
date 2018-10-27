package name.saifmahmud.cse423.lab2;

import com.jogamp.opengl.GLAutoDrawable;
import name.saifmahmud.cse423.AbstractAssignment;
import name.saifmahmud.cse423.CircularArrayList;
import name.saifmahmud.cse423.Point;
import name.saifmahmud.cse423.line.AbstractAlgorithm;
import name.saifmahmud.cse423.line.Midpoint;

import javax.swing.*;

public class Star extends AbstractAssignment {
    public static void main(String[] args) {
        Star star = new Star();
        canvas.addGLEventListener(star);
        canvas.setSize(500, 500);

        JFrame frame = new JFrame("Star Lab");

        frame.getContentPane().add(canvas);
        frame.setSize(canvas.getPreferredSize());
        frame.setVisible(true);

        star.algo = new Midpoint(canvas.getPreferredSize());
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        drawZoneLines(drawable);

        CircularArrayList<Point> points = new CircularArrayList<>();

        for (double i = 90; i < 450; i += 72) {
            points.add(Point.fromPolar(200, i));
        }

        for (int i = 0; i < points.size(); i++) {
            Point start = points.get(i);
            Point skip1 = points.get(i - 1);
            Point skip2 = points.get(i + 1);

            for (Point end : points) {
                if (end.equals(start) || end.equals(skip1) || end.equals(skip2)) {
                    continue;
                }

                ((AbstractAlgorithm) algo).draw(drawable, start, end);
            }
        }
    }
}
