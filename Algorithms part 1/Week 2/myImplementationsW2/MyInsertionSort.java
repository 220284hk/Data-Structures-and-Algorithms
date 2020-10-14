package myImplementationsW2;

import java.util.Arrays;
import java.util.Random;

public class MyInsertionSort {
    private int[] array = new int[10];

    public MyInsertionSort() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            array[i] = random.nextInt(100);
        }
    }

    public static int[] sort(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
        return array;
    }

    public int[] getArray() {
        return array;
    }


    public String toString() {
        return "MySelectionSort{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
