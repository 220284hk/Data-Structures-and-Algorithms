package myImplementationsW3;

public class MyPartition {
    public static int[] partition(int[] array) {
        int k = array[0];

        int low = 1;
        int high = array.length - 1;

        while (low != high + 1) { // cross paths
            while (array[low] < k) {
                low++;
                if (low == high + 1) break;
            }
            if (low == high + 1) break;
            if (array[high] <= k) {
                swap(array, low, high);
            }
            high--;
        }
        // swap array[low] with array[0]
        swap(array, high, 0);

        return array;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
