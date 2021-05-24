public class Main {
    public static void main(String[] arguments) {
        int size = 100;
        Points points = new Points(size);
        points.print();
        points.bubbleSort();
        System.out.println("Sorted:");
        points.print();
    }
}
