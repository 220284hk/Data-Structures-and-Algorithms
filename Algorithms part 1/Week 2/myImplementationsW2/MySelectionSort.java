package myImplementationsW2;

import java.util.Arrays;
import java.util.Random;

public class MySelectionSort {
    private int[] array = new int[10];

    public MySelectionSort() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            array[i] = random.nextInt(100);
        }
    }

    public static int[] sort(int[] array) {
        int min;
        for (int i = 0; i < array.length; i++) {
            min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) min = j;
            }
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
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
