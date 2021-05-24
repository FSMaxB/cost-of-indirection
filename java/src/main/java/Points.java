import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Points {
    private final ArrayList<Point> points;

    public Points(int size) {
        ArrayList<Point> points = new ArrayList<>();

        this.points = IntStream
                .range(0, size)
                .map(number -> size - number) // reverse the number, this creates the worst case for bubble sort
                .boxed()
                .map(number -> new Point(number / 10, number % 10))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Points(List<Point> points) {
        this.points = new ArrayList<>(points);
    }

    public double averageLength() {
        double totalLength = 0;
        int count = 0;
        for (Point point : this.points) {
            count += 1;
            totalLength += point.length();
        }

        return totalLength / count;
    }

    public double averageLengthViaStream() {
        return this.points
                .stream()
                .collect(Collectors.averagingDouble(Point::length));
    }

    public void print() {
        for (Point point : this.points) {
            System.out.println(point);
        }
    }
}
