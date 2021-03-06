import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] arguments) {
        int size = 300_000_000;
        testImplementation(ForLoopPoints::new, size);
        testImplementation(StreamPoints::new, size);
        testImplementation(InlinePoints::new, size);
        testImplementation(InlineArrayListPoints::new, size);
    }

    private static void testImplementation(Function<Stream<Point>, Points> constructor, int size) {
        long heapOverhead = heapSize();

        warmJIT(constructor);

        Points points = constructor.apply(createPoints(size));
        final long mib = 1024 * 1024;
        long sizeEstimate = heapSize() - heapOverhead;

        OutputAndSeconds<Double> outputAndSeconds = computeAndTime(points::averageLength);
        System.out.println(points.getClass().getSimpleName() + ": Average length: " + outputAndSeconds.output + " (" + outputAndSeconds.seconds + "s) (ca. " + (sizeEstimate / mib) + "MiB)");
    }

    private static void warmJIT(Function<Stream<Point>, Points> constructor) {
        Points points = constructor.apply(createPoints(1_000));
        points.averageLength();
    }

    private static Stream<Point> createPoints(int size) {
        return IntStream
                .range(0, size)
                .boxed()
                .map(number -> new Point(number / 10, number % 10));
    }

    private static <T> OutputAndSeconds<T> computeAndTime(Supplier<T> supplier) {
        long before = System.nanoTime();
        T output = supplier.get();
        double seconds = (System.nanoTime() - before) / 1e9;

        return new OutputAndSeconds<>(output, seconds);
    }

    private static long heapSize() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();

        return runtime.totalMemory() - runtime.freeMemory();
    }
}

class OutputAndSeconds<Output> {
    public final Output output;
    public final double seconds;

    public OutputAndSeconds(Output output, double seconds) {
        this.output = output;
        this.seconds = seconds;
    }
}