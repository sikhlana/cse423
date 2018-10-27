package name.saifmahmud.cse423.clipping.line;

import com.jogamp.opengl.GLAutoDrawable;
import name.saifmahmud.cse423.Point;
import name.saifmahmud.cse423.clipping.AbstractAlgorithm;

import java.awt.*;

public class CohenSutherland extends AbstractAlgorithm {
    final private static byte LEFT 	= 0b0001;
    final private static byte RIGHT 	= 0b0010;
    final private static byte BELOW	= 0b0100;
    final private static byte ABOVE 	= 0b1000;

    public CohenSutherland(Dimension dimension, Dimension canvas) {
        super(dimension, canvas);
    }

    public void clip(GLAutoDrawable drawable, Point start, Point end) {
        //dda.draw(drawable, start, end);

        byte outCode1 = computeOutCode(start.x, start.y);
        byte outCode2 = computeOutCode(end.x, end.y);
        boolean accept = false;

        while (true) {
            if ((outCode1 | outCode2) == 0) {
                accept = true;
                break;
            }

            if ((outCode1 & outCode2) != 0) {
                break;
            }

            double x = 0, y = 0;
            byte outCode = outCode1 != 0 ? outCode1 : outCode2;

            if ((outCode & ABOVE) > 0) {
                x = start.x + (end.x - start.x) * (ymax - start.y) / (end.y - start.y);
                y = ymax;
            } else if ((outCode & BELOW) > 0) {
                x = start.x + (end.x - start.x) * (ymin - start.y) / (end.y - start.y);
                y = ymin;
            } else if ((outCode & RIGHT) > 0) {
                x = xmax;
                y = start.y + (end.y - start.y) * (ymax - start.y) / (end.y - start.y);
            } else if ((outCode & LEFT) > 0) {
                x = xmin;
                y = start.y + (end.y - start.y) * (ymin - start.y) / (end.y - start.y);
            }

            if (outCode == outCode1) {
                start = new Point(x, y);
                outCode1 = computeOutCode(x, y);
            } else {
                end = new Point(x, y);
                outCode2 = computeOutCode(x, y);
            }
        }

        if (accept) {
            dda.draw(drawable, start, end);
        }
    }

    private byte computeOutCode(double x, double y) {
        byte outCode = 0;

        if (x < xmin) {
            outCode |= LEFT;
        } else if (x > xmax) {
            outCode |= RIGHT;
        }

        if (y < ymin) {
            outCode |= BELOW;
        } else if (y > ymax) {
            outCode |= ABOVE;
        }

        return outCode;
    }
}
