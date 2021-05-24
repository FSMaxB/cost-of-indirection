import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public ArrayList<Point> list() {
        return new ArrayList<>(this.points);
    }

    public void print() {
        for (Point point : this.points) {
            System.out.println(point);
        }
    }
}
