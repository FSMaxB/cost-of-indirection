import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ForLoopPoints implements Points {
    private final ArrayList<Point> points;

    public ForLoopPoints(Stream<Point> points) {
        this.points = points.collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public double averageLength() {
        long totalLength = 0;
        for (Point point : this.points) {
            totalLength += point.length();
        }

        return ((double)totalLength) / this.points.size();
    }
}
