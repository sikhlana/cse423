package name.saifmahmud.cse423.line;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import name.saifmahmud.cse423.Point;

import java.awt.*;

public class Midpoint extends AbstractAlgorithm {
    public Midpoint(Dimension dimension) {
        super(dimension);
    }

    @Override
    public void draw(GLAutoDrawable drawable, Point s, Point e) {
        Point start = new Point(s.x, s.y);
        Point end = new Point(e.x, e.y);

        double dx, dy, x, y, x2;
        double cx = start.x, cy = start.y;

        start.x = 0; start.y = 0;
        end.x -= cx; end.y -= cy;

        Zone zone = Zone.getZone(start, end);

        switch (zone) {
            case THREE:
            case FOUR:
            case SEVEN:
            default:
                dx = Math.abs(end.x - start.x);
                dy = Math.abs(end.y - start.y);
                x = start.x;
                y = start.y;
                x2 = Math.abs(end.x);
                break;

            case ONE:
            case TWO:
            case FIVE:
            case SIX:
                dx = Math.abs(end.y - start.y);
                dy = Math.abs(end.x - start.x);
                x = start.y;
                y = start.x;
                x2 = Math.abs(end.y);
                break;
        }

        double d = dy - (dx / 2);

        final GL2 gl = drawable.getGL().getGL2();
        gl.glPointSize(1);
        gl.glBegin(GL2.GL_POINTS);

        glDraw(gl, x, y, zone, cx, cy);

        while (x < x2) {
            x++;

            if (d < 0) {
                d = d + dy;
            } else {
                d += (dy - dx);
                y++;
            }

            glDraw(gl, x, y, zone, cx, cy);
        }

        gl.glEnd();
    }

    private void glDraw(GL2 gl, double x, double y, Zone zone, double cx, double cy) {
        double px, py;

        switch (zone) {
            default:
                px = x; py = y;
                break;

            case ONE:
                px = y; py = x;
                break;

            case TWO:
                px = - y; py = x;
                break;

            case THREE:
                px = - x; py = y;
                break;

            case FOUR:
                px = - x; py = - y;
                break;

            case FIVE:
                px = - y; py = - x;
                break;

            case SIX:
                px = y; py = - x;
                break;

            case SEVEN:
                px = x; py = - y;
                break;
        }

        gl.glVertex2d(2 * (px + cx) / dimension.width, 2 * (py + cy) / dimension.height);
    }

    enum Zone {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN;

        public static Zone getZone(Point start, Point end) {
            double x = end.x - start.x;
            double y = end.y - start.y;

            if (x == 0) {
                return ZERO;
            }

            double angle = Math.toDegrees(Math.atan(y / x));

            if (x < 0 && y < 0) {
                angle += 180;
            }

            if (angle < 0) {


                if (x < 0) {
                    angle += 180;
                } else {
                    angle += 360;
                }
            }

            if (angle <= 45) {
                return ZERO;
            }

            if (angle <= 90) {
                return ONE;
            }

            if (angle <= 135) {
                return TWO;
            }

            if (angle <= 180) {
                return THREE;
            }

            if (angle <= 225) {
                return FOUR;
            }

            if (angle <= 270) {
                return FIVE;
            }

            if (angle <= 315) {
                return SIX;
            }

            return SEVEN;
        }
    }
}
