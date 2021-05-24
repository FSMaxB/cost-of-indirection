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

    public void bubbleSort() {
        if (this.points.size() <= 1) {
            return;
        }

        for (int upperBound = this.points.size() - 1; upperBound >= 1; upperBound--) {
            for (int index = 0; index < upperBound; index++) {
                Point first = this.points.get(index);
                Point second = this.points.get(index + 1);

                int comparison = first.compareTo(second);
                if (comparison > 0) { // swap
                    this.points.set(index, second);
                    this.points.set(index + 1, first);
                }
            }
        }
    }

    public void print() {
        for (Point point : this.points) {
            System.out.println(point);
        }
    }
}
