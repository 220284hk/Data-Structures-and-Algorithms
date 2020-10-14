package myImplementationsW3;

public class MyPartitionGenerics {
    public static int partition(Comparable[] array, int low, int high) {
        int partitionIndex = low++;
        Comparable partitionValue = array[partitionIndex];

        while (low != high + 1) { // cross paths
            while (array[low].compareTo(partitionValue) < 0) {
                low++;
                if (low == high + 1) break;
            }
            if (low == high + 1) break;
            if (array[high].compareTo(partitionValue) < 0) {
                swap(array, low, high);
            }
            high--;
        }
        // swap array[low] with array[0]
        swap(array, high, partitionIndex);

        return high;
    }

    private static void swap(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
