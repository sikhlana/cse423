package name.saifmahmud.cse423;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }

        return this.x == ((Point) obj).x && this.y == ((Point) obj).y;
    }

    public static Point fromPolar(double radius, double angle) {
        return new Point(
                radius * Math.cos(Math.toRadians(angle)),
                radius * Math.sin(Math.toRadians(angle))
        );
    }
}
