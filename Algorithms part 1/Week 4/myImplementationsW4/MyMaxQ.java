package myImplementationsW4;

import java.util.Arrays;

public class MyMaxQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public MyMaxQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Key max() {
        if (isEmpty()) return null;
        return pq[1];
    }

    public void insert(Key key) {
        pq[++N] = key;
        swim(N);
    }

    public String toString() {
        return "pq=" + Arrays.toString(pq) +
                ", N=" + N +
                '}';
    }

    public Key delMax() {
        if (N < 1) return null;
        exch(1, N);
        Key max = pq[N--];
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    //
    private void swim(int k) {
        int w = k / 2;
        while (w > 0) {
            if (less(w, k)) {
                exch(k, w);
                w = w / 2;
                k = k / 2;
            } else {
                break;
            }
        }
    }

    public void sink(int k) {
        int w = k * 2;
        while (w < N) {
            if (less(k, w) || less(k, w + 1)) {
                if (less(w, w + 1)) {
                    exch(k, w + 1);
                    k = w + 1;
                } else {
                    exch(k, w);
                    k = w;
                }
                w = k * 2;
            } else {
                break;
            }

        }
    }

    //
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

//    public Key[] heapSort() {
//        Key[] heap = pq.clone();
//        int n = N;
//        while (n > 0) {
//
//        }
//    }
}
