public class Main {
    public static void main(String[] arguments) {
        double warmTime = executionTimeInSeconds(Main::warmJIT);
        System.out.println("Warming JIT took " + warmTime + " seconds.");


        int size = 100_000;
        Points points = new Points(size);
        System.out.println("Finished constructing points.");

        double sortTime = executionTimeInSeconds(points::bubbleSort);
        System.out.println("Sorting took " + sortTime + " seconds");
    }

    private static void warmJIT() {
        System.out.println("Warming JIT.");
        (new Points(1_000)).bubbleSort();
        System.out.println("Finished warming JIT.");
    }

    private static double executionTimeInSeconds(Runnable runnable) {
        long before = System.nanoTime();
        runnable.run();
        long after = System.nanoTime();
        return (after - before) / 1e9;
    }
}
