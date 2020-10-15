package myImplementationsW3;

import java.util.Arrays;

public class MyMerge {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return; // consider smallest sub array to see why it must be equal as well
        int mid = (hi - lo) / 2 + lo;
        sort(a, aux, lo, mid);
        assert isSorted(a, lo, mid);
        sort(a, aux, mid + 1, hi);
        assert isSorted(a, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
        assert isSorted(a, lo, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo); // copying values to aux array

        int j = lo; // iterator for aux
        int k = mid + 1;
        while (j <= hi) {
            if (lo == k) {
                a[j++] = aux[k++];
            } else if (k > hi) {
                a[j++] = aux[lo++];
            } else if (aux[lo].compareTo(aux[k]) < 0) {
                a[j++] = aux[lo++];
            } else if (aux[lo].compareTo(aux[k]) > 0) {
                a[j++] = aux[k++];
            }
        }
        assert isSorted(a, lo, hi);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi - 1; i++) {
            if (a[i].compareTo(a[i + 1]) > 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        Integer[] temp = new Integer[]{4, 3, 2, 1};
        String[] temp = new String[]{"z", "x", "y"};
        sort(temp);
        System.out.println(Arrays.toString(temp));
    }
}
