package myImplementationsW2;

public class MyStackList {
    private int count = 0;
    private Node head;

    private class Node {
        String item;
        Node next = null;
    }

    public void push(String s) {
        count++;
        Node oldHead = head;
        head = new Node();
        head.item = s;
        head.next = oldHead;
    }

    public String pop() {
        if (head == null) {
            System.out.println("Stack is empty!");
            return null;
        }
        count--;
        String value = head.item;
        head = head.next;
        return value;
    }

    public String toString() {
        Node pointer = head;
        StringBuilder sb = new StringBuilder();
        while (pointer != null) {
            sb.append(pointer.item).append(" -> ");
            pointer = pointer.next;
        }
        sb.append(pointer);
        sb.append("\nLength of queue is: ").append(count);
        return sb.toString();
    }
}
