package myImplementationsW3;

public class MyPartition {
    public static int partition(int[] array, int low, int high) {
        int partitionIndex = low++;
        int partitionValue = array[low];

        while (low != high + 1) { // cross paths
            while (array[low] < partitionValue) {
                low++;
                if (low == high + 1) break;
            }
            if (low == high + 1) break;
            if (array[high] <= partitionValue) {
                swap(array, low, high);
            }
            high--;
        }
        // swap array[low] with array[0]
        swap(array, high, partitionIndex);

        return high;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
