import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPoints implements Points {
    private final ArrayList<Point> points;

    public StreamPoints(Stream<Point> points) {
        this.points = points.collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public double averageLength() {
        long totalLength = this.points
                .stream()
                .mapToLong(Point::length)
                .sum();
        return ((double)totalLength) / this.points.size();
    }
}
