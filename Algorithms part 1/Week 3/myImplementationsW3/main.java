package myImplementationsW3;

import java.util.Arrays;

public class main {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {

            System.out.println("Iteration i = " + i);
            Integer[] x = myImplementationsW2.main.randomUniqueIntArrayGenerator(20);
            System.out.println(Arrays.toString(x));
            System.out.println(MySelectionGenerics.findKthLargest(x, 1));
            MySelectionGenerics.findKthLargest(x, 1);
            System.out.println(Arrays.toString(x));
        }

    }

}
