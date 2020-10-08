package myImplementationsW2;

import java.util.Arrays;

public class MyStackArray {
    private int pointer = 0;
    private int size = 1;
    private String[] stack;

    public MyStackArray() {
        stack = new String[size];
    }

    public String pop() {
        if (pointer == 0) return null;          // prevents popping when there is nothing left
        String x = stack[--pointer];
        stack[pointer] = null;                  // prevents loitering. No more references to the string at stack[pointer]
        System.out.println("Popped " + x);
        if (pointer <= stack.length / 4) {
            String[] newStack = new String[stack.length / 2];
            if (pointer + 1 >= 0) System.arraycopy(stack, 0, newStack, 0, pointer + 1);
            stack = newStack;
            System.out.println("This stack has been made: " + Arrays.toString(newStack));
        }
        return x;
    }

    public void push(String word) {
        if (word.equals("-")) {
            pop();
            return;
        } else if (pointer == stack.length) {
            resize();
            System.out.println("Stack size increased! Current size: " + size);
        }
        stack[pointer++] = word;
    }

    private void resize() {
        String[] tempStack = new String[size * 2];
        System.arraycopy(stack, 0, tempStack, 0, stack.length);
        stack = tempStack;
        tempStack = null;
        size *= 2;
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
//        for (String x : stack) {
//            sb.append(x).append("\n");
//        }
        int pv = pointer;
        while (pv > 0) {
            sb.append(" ___________\n");
            sb.append("|_____").append(stack[--pv]).append("_____|\n");
        }
        return sb.toString();
    }

}
