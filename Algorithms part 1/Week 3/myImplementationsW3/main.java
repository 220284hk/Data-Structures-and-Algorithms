package myImplementationsW3;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Arrays;
import java.util.Comparator;

public class main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[]{3, 30, 34, 5, 9});

        System.out.println(solution.solution(new int[]{3, 30, 34, 5, 9}));
    }

    //[1, 5, 2, 6, 3, 7, 4]	[[2, 5, 3], [4, 4, 1], [1, 7, 3]]	[5, 6, 3]
//  [3, 30, 34, 5, 9]
    public static class Solution {
        public String solution(int[] numbers) {
            String answer = "";
            String[] array = new String[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                array[i] = String.valueOf(numbers[i]);
            }

            Arrays.sort(array, new MyComparator());
            System.out.println("Sorted array: " + Arrays.toString(array));

            for (int i = numbers.length - 1; i >= 0; i--) {
                answer += array[i];
            }
            return answer;
        }

        @SuppressFBWarnings("SE_COMPARATOR_SHOULD_BE_SERIALIZABLE")
        public class MyComparator implements Comparator<String> {
            public int compare(String o1, String o2) {
                boolean swapped = false;
                int val;
                int lS1 = o1.length(), lS2 = o2.length();
                if (lS1 == lS2) {
                    return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
                }

                if (lS1 > lS2) {
                    swapped = true;
                    String temp = o1;
                    o1 = o2;
                    o2 = temp;
                    lS1 = lS2;
                }

                for (int i = 0; i < lS1; i++) {
                    val = Integer.compare(o1.charAt(i), o2.charAt(i));
                    if (val != 0) {
                        if (swapped) return val * -1;
                        return val;
                    }
                }
                val = Integer.compare(o1.charAt(lS1 - 1), o2.charAt(lS1));
                if (swapped) return -1 * val;
                return val;
            }
        }

    }


//    @SuppressFBWarnings("SE_COMPARATOR_SHOULD_BE_SERIALIZABLE")
//    public static class MyComparator implements Comparator<String> {
//        public int compare(String o1, String o2) {
//            System.out.println("o1 " + o1 + " o2 " + o2);
//            int val;
//            int lS1 = o1.length(), lS2 = o2.length();
//            if (lS1 == lS2) {
//                return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
//            }
//            if (lS1 < lS2) {
//                for (int i = 0; i < lS1; i++) {
//                    val = Integer.compare(o1.charAt(i), o2.charAt(i));
//                    if (val != 0) {
//                        return val;
//                    }
//                }
//                val = Integer.compare(o1.charAt(lS1 - 1), o2.charAt(lS1));
//            } else {
//                for (int i = 0; i < lS2; i++) {
//                    val = Integer.compare(o1.charAt(i), o2.charAt(i));
//                    if (val != 0) {
//                        return val;
//                    }
//                }
//                val = Integer.compare(o1.charAt(lS2), o2.charAt(lS2 - 1));
//            }
//            return val;
//        }
//    }

//    @SuppressFBWarnings("SE_COMPARATOR_SHOULD_BE_SERIALIZABLE")
//    public static class MyComparator implements Comparator<String> {
//        public int compare(String o1, String o2) {
//            System.out.println("o1 " + o1 + " o2 " + o2);
//            int val = 1;
//            int lS1 = o1.length(), lS2 = o2.length();
//            if (lS1 == lS2) {
//                return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
//            }
//            if (lS1 < lS2) {
//                val = -1;
//                String temp = o1;
//                o1 = o2;
//                o2 = temp;
//                lS2 = lS1;
//            }
//            for (int i = 0; i < lS2; i++) {
//                val *= Integer.compare(o1.charAt(i), o2.charAt(i));
//                if (val != 0) {
//                    return val;
//                }
//            }
//            System.out.println("o1 " + o1 + " o2 " + o2);
//            int o1Char = o1.charAt(lS2), o2Char = o2.charAt(lS2 - 1);
//            System.out.println("o1Char: " + o1Char + " o2Char: " + o2Char + " comp: " + Integer.compare(o1Char, o2Char));
//            val *= Integer.compare(o1Char, o2Char);
//            return val;
////            if (o1Char > o2Char) {
////                return 1;
////            } else {
////                return -1;
////            }
//        }
//    }


    @SuppressFBWarnings("NM_METHOD_NAMING_CONVENTION")

    public static void selectionSort(Comparable[] a) {
        int minDex;
        for (int i = 0; i < a.length; i++) {
            minDex = i;
            for (int j = i; j < a.length; j++) {
                if (a[j].compareTo(a[minDex]) < 0) {
                    minDex = j;
                }
            }
            swap(a, minDex, i);
        }
    }

    public static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }
}
