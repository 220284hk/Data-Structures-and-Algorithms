package myImplementationsW2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class main {

    public static void main(String[] args) {
//        queueLinkedTest();
//        test1();
//        test2();
//        MyInsertionSort ms = new MyInsertionSort();
//        System.out.println(ms);
//        System.out.println(ms);
//        int[] sorted = MySelectionSort.sort(ms.getArray());
//        for (int i = 0; i < sorted.length; i++) {
//            System.out.print(sorted[i] + ", ");
//        }
//        System.out.println(ms);
        int[] myArray = new int[]{16, 21, 38, 46, 43, 18, 32, 21, 28, 45, 26};
        System.out.println(Arrays.toString(myShuffle(myArray)));
    }

    public static Integer[] randomUniqueIntArrayGenerator(Integer n) {
        Random random = new Random();
        Integer[] randomArray = new Integer[n];
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            Integer temp = random.nextInt(50);
            while (set.contains(temp)) {
                temp = random.nextInt(50);
            }
            randomArray[i] = temp;
            set.add(temp);
        }
        return randomArray;
    }

    private static int[] myShuffle(int[] array) {
        int size = array.length;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int temp1 = random.nextInt(i + 1);
            int temp2 = array[i];
            array[i] = temp1;
            array[temp1] = temp2;
        }
        return array;
    }

//    private static void queueLinkedTest() {
//        MyQueueLinkedOne queue = new MyQueueLinkedOne();
//        queue.push("hello");
//        System.out.println(queue);
//        for (int i = 0; i < 16; i++) {
//            queue.push(String.valueOf(i));
//            if (i == 5 || i == 7) queue.pop();
//            System.out.println(queue);
//        }
//        queue.push("100");
//        System.out.println(queue);
//        for (int i = 0; i < 30; i++) {
//            queue.pop();
//            System.out.println(queue);
//        }
//        queue.push("5");
//        System.out.println(queue);
//    }
//
//    private static void queueArrayTest() {
//        MyQueueArray queue = new MyQueueArray();
//        for (int i = 0; i < 16; i++) {
//            queue.push(String.valueOf(i));
//            if (i == 5 || i == 7) queue.pop();
//            System.out.println(queue);
//        }
//        queue.push("100");
//        System.out.println(queue);
//        for (int i = 0; i < 30; i++) {
//            queue.pop();
//            System.out.println(queue);
//        }
//        queue.push("5");
//        System.out.println(queue);
//
//    }
//
//    private static void test1() {
//        MyStackArray myStackArray = new MyStackArray();
//        myStackArray.push("1");
//        myStackArray.push("2");
//        myStackArray.push("3");
//        myStackArray.push("4");
//        myStackArray.push("5");
//        myStackArray.push("6");
//        myStackArray.push("7");
//        myStackArray.push("8");
//        System.out.println(myStackArray);
//        myStackArray.pop();
//        System.out.println(myStackArray);
//        myStackArray.pop();
//        System.out.println(myStackArray);
//        myStackArray.pop();
//        System.out.println(myStackArray);
//        myStackArray.pop();
//        System.out.println(myStackArray);
//        myStackArray.pop();
//        myStackArray.pop();
//        System.out.println(myStackArray);
//    }
//
//    public static void test2() {
//        MyStackList myStackList = new MyStackList();
//        myStackList.pop();
//        myStackList.push("hello");
//        System.out.println(myStackList);
//        myStackList.push("hello");
//        System.out.println(myStackList);
//        myStackList.push("hello");
//        myStackList.push("hello");
//        myStackList.push("1");
//        myStackList.push("2");
//        myStackList.push("3");
//        myStackList.push("-");
//        myStackList.push("4");
//        myStackList.push("5");
//        System.out.println(myStackList);
//        myStackList.pop();
//        System.out.println(myStackList);
//        myStackList.pop();
//        System.out.println(myStackList);
//        myStackList.pop();
//        System.out.println(myStackList);
//        myStackList.pop();
//        System.out.println(myStackList);
//    }
}
