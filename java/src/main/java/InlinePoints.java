import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InlinePoints implements Points {
    private final int[] points;

    public InlinePoints(Stream<Point> points) {
        ArrayList<Point> temporaryPoints = points.collect(Collectors.toCollection(ArrayList::new));

        this.points = new int[temporaryPoints.size() * 2];
        for (int index = 0; index < temporaryPoints.size(); index++) {
            Point point = temporaryPoints.get(index);
            this.points[2 * index] = point.x();
            this.points[2 * index + 1] = point.y();
        }
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public double averageLength() {
        long totalLength = 0;
        for (int index = 0; index < (this.points.length / 2); index++) {
            int x = this.points[2 * index];
            int y = this.points[2 * index + 1];
            totalLength += x + y;
        }

        return ((double)totalLength) / ((double)(this.points.length / 2));
    }
}
