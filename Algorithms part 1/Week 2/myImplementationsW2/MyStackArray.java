package myImplementationsW2;

public class MyStackArray {
    private int pointer = 0;
    private int size = 1;
    private String[] stack;

    public MyStackArray() {
        stack = new String[size];
    }

    public String pop() {
        if (pointer == 0) return null;
        String x = stack[--pointer];
        stack[pointer] = null;
        System.out.println("Popped " + x);
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
//        for (int i = 0; i < stack.length; i++) {
//            tempStack[i] = stack[i];
//        }
        stack = tempStack;
        tempStack = null;
        size *= 2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String x : stack) {
            sb.append(x).append(" ");
        }
        return sb.toString();
    }
}
