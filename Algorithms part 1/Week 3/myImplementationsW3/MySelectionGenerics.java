package myImplementationsW3;

public class MySelection {

    // k = 1 finds the largest value;
    public static int findKthLargest(int[] array, int k) {
        k = array.length - k;
        int lb = 0, ub = array.length - 1;

        int j = MyPartition.partition(array, lb, ub);
        while (j != k) {
            if (j > k) {
                ub = j - 1;
            } else {
                lb = j + 1;
//                j = MyPartition.partition(array, j + 1, ub);
            }
            j = MyPartition.partition(array, lb, ub);
        }
        return array[k];
    }
}
