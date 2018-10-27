package name.saifmahmud.cse423.lab4;

import com.jogamp.opengl.GLAutoDrawable;
import name.saifmahmud.cse423.AbstractAssignment;
import name.saifmahmud.cse423.Point;
import name.saifmahmud.cse423.clipping.line.CohenSutherland;

import javax.swing.*;
import java.awt.*;

public class Clipping extends AbstractAssignment {
    public static void main(String[] args) {
        Clipping clipping = new Clipping();
        canvas.addGLEventListener(clipping);
        canvas.setSize(500, 500);

        JFrame frame = new JFrame("Floral Design");

        frame.getContentPane().add(canvas);
        frame.setSize(canvas.getPreferredSize());
        frame.setVisible(true);

        clipping.algo = new CohenSutherland(
            canvas.getPreferredSize(), new Dimension(400, 300)
        );
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        ((CohenSutherland) algo).drawCanvas(drawable);

        Point start = new Point(0, - 200);
        Point end = new Point(300, 500);

        ((CohenSutherland) algo).clip(drawable, start, end);
    }
}
