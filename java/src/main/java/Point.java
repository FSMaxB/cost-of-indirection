public class Point implements Comparable<Point> {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    @Override
    public int compareTo(Point other) {
        // compare x first
        if (this.x < other.x) {
            return -1;
        } else if (this.x > other.x) {
            return 1;
        }

        // if x are the same, compare y
        if (this.y < other.y) {
            return -1;
        } else if (this.y > other.y) {
            return 1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", this.x, this.y);
    }
}
