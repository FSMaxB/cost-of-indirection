import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Points {
    private final ArrayList<Point> points;

    public Points(int size) {
        this.points = IntStream
                .range(0, size)
                .boxed()
                .map(number -> new Point(number / 10, number % 10))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public double averageLengthViaForLoop() {
        long totalLength = 0;
        for (Point point : this.points) {
            totalLength += point.length();
        }

        return ((double)totalLength) / this.points.size();
    }

    public double averageLengthViaStream() {
        long totalLength = this.points
                .stream()
                .mapToLong(Point::length)
                .sum();
        return ((double)totalLength) / this.points.size();
    }


    public void print() {
        for (Point point : this.points) {
            System.out.println(point);
        }
    }
}
