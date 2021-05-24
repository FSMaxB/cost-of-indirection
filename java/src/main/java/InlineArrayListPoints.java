import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InlineArrayListPoints implements Points {
    private final ArrayList<Integer> points;

    public InlineArrayListPoints(Stream<Point> points) {
        ArrayList<Point> temporaryPoints = points.collect(Collectors.toCollection(ArrayList::new));

        this.points = new ArrayList<>(2 * temporaryPoints.size());
        for (Point point : temporaryPoints) {
            this.points.add(point.x());
            this.points.add(point.y());
        }
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public double averageLength() {
        long totalLength = 0;
        for (int index = 0; index < (this.points.size() / 2); index++) {
            int x = this.points.get(2 * index);
            int y = this.points.get(2 * index + 1);
            totalLength += x + y;
        }

        return ((double)totalLength) / ((double)(this.points.size() / 2));
    }
}
