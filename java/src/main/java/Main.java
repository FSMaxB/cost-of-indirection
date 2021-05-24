import java.util.function.Supplier;

public class Main {
    public static void main(String[] arguments) {
        double warmJITSeconds = computeAndTime(supplierFromRunnable(Main::warmJIT)).seconds;
        System.out.println("Warming JIT took " + warmJITSeconds + " seconds.");


        int size = 300_000_000;
        Points points = new Points(size);
        System.out.println("Finished constructing points.");

        OutputAndSeconds<Double> viaForLoop = computeAndTime(points::averageLengthViaForLoop);
        System.out.println("Average length via for loop: " + viaForLoop.output + " (" + viaForLoop.seconds + "s)");

        OutputAndSeconds<Double> viaStream = computeAndTime(points::averageLengthViaStream);
        System.out.println("Average length via for stream: " + viaStream.output + " (" + viaStream.seconds + "s)");
    }

    private static void warmJIT() {
        System.out.println("Warming JIT.");
        Points points = new Points(1_000);
        points.averageLengthViaForLoop();
        points.averageLengthViaStream();
        System.out.println("Finished warming JIT.");
    }

    private static <T> OutputAndSeconds<T> computeAndTime(Supplier<T> supplier) {
        long before = System.nanoTime();
        T output = supplier.get();
        double seconds = (System.nanoTime() - before) / 1e9;

        return new OutputAndSeconds<>(output, seconds);
    }

    private static Supplier<Void> supplierFromRunnable(Runnable runnable) {
        return () -> {
            runnable.run();
            return null;
        };
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
