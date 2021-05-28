import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InlineArrayListPoints implements Points {
    private final ArrayList<Integer> xyCoordinates;

    public InlineArrayListPoints(Stream<Point> points) {
        ArrayList<Point> temporaryPoints = points.collect(Collectors.toCollection(ArrayList::new));

        this.xyCoordinates = new ArrayList<>(2 * temporaryPoints.size());
        for (Point point : temporaryPoints) {
            this.xyCoordinates.add(point.x());
            this.xyCoordinates.add(point.y());
        }
    }

    @Override
    public double averageLength() {
        long totalLength = 0;
        for (int index = 0; index < (this.xyCoordinates.size() / 2); index++) {
            int x = this.xyCoordinates.get(2 * index);
            int y = this.xyCoordinates.get(2 * index + 1);
            totalLength += x + y;
        }

        return ((double)totalLength) / ((double)(this.xyCoordinates.size() / 2));
    }
}
