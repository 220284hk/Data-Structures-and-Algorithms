package myImplementations;

@edu.umd.cs.findbugs.annotations.SuppressFBWarnings("RC_REF_COMPARISON")
public class BinarySearch {
    private static Integer[] staticSorted = new Integer[10];
    private Integer[] sortedArray = new Integer[10];
    private int iterationCount = 0;

    public static void main(String[] args) {
//        BinarySearch bs = new BinarySearch();
//        bs.sortedArray®
//        System.out.println(bs.find(4));
        for (int i = 0; i < 10; i++) {
            staticSorted[i] = i;
        }
        System.out.println(find(Integer.valueOf(args[0]), staticSorted));

    }

    public BinarySearch() {
        for (int i = 0; i < 10; i++) {
            sortedArray[i] = i;
        }
    }

    public Integer find(Integer T) {
        System.out.println("Trying to find the value: " + T);
        int L = 0, R = 9;
        int m = (L + R) / 2;

        while (true) {
            iterationCount++;
            if (sortedArray[m] == T) {
                System.out.println("The iteration count is: " + iterationCount);
                return m;
            } else if (sortedArray[m] > T) {
                R = m - 1;
            } else {
                L = m + 1;
            }
            m = (R + L) / 2;
            if (L > R) {
                System.out.println("The iteration count is: " + iterationCount);
                return -1;
            }
        }
    }

    public static Integer find(Integer T, Integer[] sortedArray) {
        int L = 0, R = sortedArray.length - 1, m;
        while (true) {
            m = (L + R) / 2;
            if (sortedArray[m] == T) {
                return m;
            } else if (sortedArray[m] > T) {
                R = m - 1;
            } else {
                L = m + 1;
            }
            m = (R + L) / 2;
            if (L > R) {
                return -1;
            }
        }
    }
}
