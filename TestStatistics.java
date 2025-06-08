package statistics;

import java.util.Random;

public class TestStatistics {

    private Statistics statistics;

    public TestStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public static void main(String[] args) {
        // Test với MyArrayList
        System.out.println("========== TEST MYARRAYLIST ==========");
        TestStatistics testArray = new TestStatistics(null);
        testArray.testMyArrayList();

        // Test với MyLinkedList
        System.out.println("\n========== TEST MYLINKEDLIST ==========");
        TestStatistics testLinked = new TestStatistics(null);
        testLinked.testMyLinkedList();
    }

    public void testMyArrayList() {
        Random random = new Random();
        int length = 30 + random.nextInt(21); // [30,50]

        MyArrayList list = new MyArrayList();
        for (int i = 0; i < length; i++) {
            double value = 1 + random.nextDouble() * 19; // [1.0,20.0)
            list.add(value);
        }

        this.statistics = new Statistics(list);

        System.out.println("Tập dữ liệu gốc:");
        System.out.println(list.toString());

        MyList sortedList = list.sortIncreasing();
        System.out.println("\nTập dữ liệu đã sắp xếp:");
        System.out.println(sortedList.toString());

        System.out.println("\nCác đại lượng thống kê:");
        System.out.printf("Max: %.2f\n", statistics.max());
        System.out.printf("Min: %.2f\n", statistics.min());
        System.out.printf("Kỳ vọng (Mean): %.2f\n", statistics.mean());
        System.out.printf("Phương sai (Variance): %.2f\n", statistics.variance());

        double[] ranks = statistics.rank();
        System.out.print("Rank: [");
        for (int i = 0; i < ranks.length; i++) {
            System.out.printf("%.1f", ranks[i]);
            if (i < ranks.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        double searchValue = 1 + random.nextDouble() * 19;
        int searchResult = statistics.search(searchValue);
        System.out.printf("\nKết quả tìm kiếm %.2f: %s\n",
                searchValue,
                searchResult >= 0 ? "Tìm thấy tại vị trí " + searchResult : "Không tìm thấy");
    }

    public void testMyLinkedList() {
        Random random = new Random();
        int length = 30 + random.nextInt(21); // [30,50]

        MyLinkedList list = new MyLinkedList();
        for (int i = 0; i < length; i++) {
            double value = 1 + random.nextDouble() * 19; // [1.0,20.0)
            list.add(value);
        }

        this.statistics = new Statistics(list);

        System.out.println("Tập dữ liệu gốc:");
        System.out.println(list.toString());

        MyList sortedList = list.sortIncreasing();
        System.out.println("\nTập dữ liệu đã sắp xếp:");
        System.out.println(sortedList.toString());

        System.out.println("\nCác đại lượng thống kê:");
        System.out.printf("Max: %.2f\n", statistics.max());
        System.out.printf("Min: %.2f\n", statistics.min());
        System.out.printf("Kỳ vọng (Mean): %.2f\n", statistics.mean());
        System.out.printf("Phương sai (Variance): %.2f\n", statistics.variance());

        double[] ranks = statistics.rank();
        System.out.print("Rank: [");
        for (int i = 0; i < ranks.length; i++) {
            System.out.printf("%.1f", ranks[i]);
            if (i < ranks.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        double searchValue = 1 + random.nextDouble() * 19;
        int searchResult = statistics.search(searchValue);
        System.out.printf("\nKết quả tìm kiếm %.2f: %s\n",
                searchValue,
                searchResult >= 0 ? "Tìm thấy tại vị trí " + searchResult : "Không tìm thấy");
    }
}