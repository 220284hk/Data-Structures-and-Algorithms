package myImplementationsW2;

public class MyStackArrayCheating {
    private int pointer = 0;
    private String[] stack;

    public MyStackArrayCheating(int n) {
        stack = new String[n];
    }

    public String pop() {
        if (pointer == 0) return null;
        return stack[--pointer];
    }

    public void push(String word) {
        if (pointer == stack.length) {
            System.out.println("IT's FULL!");
            return;
        }
        stack[pointer++] = word;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String x : stack) {
            sb.append(x).append(" ");
        }
        return sb.toString();
    }
}
