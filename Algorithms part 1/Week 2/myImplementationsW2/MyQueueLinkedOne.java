package myImplementationsW2;

public class MyQueueLinkedOne {
    private Node head = null;
    private int size = 1;

    private static class Node {
        private String item;
        private Node next;
    }

    public void push(String s) {
        Node last = new Node();
        last.item = s;

        if (head == null) {
            head = last;
            return;
        }

//        TODO can i use head here? Instead of just pointer? -- Absolutely not.
        Node pointer = head;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        pointer.next = last;
        size++;
    }

    public void pop() {
        if (head == null) {
            System.out.println("Empty!");
            return;
        }
        head = head.next;
        size--;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int numberOfElements() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node pointer = head;
        while (pointer != null) {
            sb.append(pointer.item).append(" -> ");
            pointer = pointer.next;
        }
        sb.append("null");
        return sb.toString();
    }
}

