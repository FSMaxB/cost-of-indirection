public class Main {
    public static void main(String[] arguments) {
        double warmTime = executionTimeInSeconds(Main::warmJIT);
        System.out.println("Warming JIT took " + warmTime + " seconds.");


        int size = 100_000_000;
        Points points = new Points(size);
        System.out.println("Finished constructing points.");

        double averageTime = executionTimeInSeconds(points::averageLength);
        System.out.println("Average length took " + averageTime + " seconds");

        double streamAverageTime = executionTimeInSeconds(points::averageLengthViaStream);
        System.out.println("Average length via Stream took " + streamAverageTime + " seconds");
    }

    private static void warmJIT() {
        System.out.println("Warming JIT.");
        Points points = new Points(1_000);
        points.averageLength();
        points.averageLengthViaStream();
        System.out.println("Finished warming JIT.");
    }

    private static double executionTimeInSeconds(Runnable runnable) {
        long before = System.nanoTime();
        runnable.run();
        long after = System.nanoTime();
        return (after - before) / 1e9;
    }
}
