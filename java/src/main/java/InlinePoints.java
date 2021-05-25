import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InlinePoints implements Points {
    private final int[] xyCoordinates;

    public InlinePoints(Stream<Point> points) {
        ArrayList<Point> temporaryPoints = points.collect(Collectors.toCollection(ArrayList::new));

        this.xyCoordinates = new int[temporaryPoints.size() * 2];
        for (int index = 0; index < temporaryPoints.size(); index++) {
            Point point = temporaryPoints.get(index);
            this.xyCoordinates[2 * index] = point.x();
            this.xyCoordinates[2 * index + 1] = point.y();
        }
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public double averageLength() {
        long totalLength = 0;
        for (int index = 0; index < (this.xyCoordinates.length / 2); index++) {
            int x = this.xyCoordinates[2 * index];
            int y = this.xyCoordinates[2 * index + 1];
            totalLength += x + y;
        }

        return ((double)totalLength) / ((double)(this.xyCoordinates.length / 2));
    }
}
