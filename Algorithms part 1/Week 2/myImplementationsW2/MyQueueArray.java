package myImplementationsW2;

import java.util.Arrays;

public class MyQueueArray {
    String[] queue;
    int front, end;
    int size = 1;

    public MyQueueArray() {
        queue = new String[size];
        front = 0;
        end = 0;
    }

    public void push(String s) {
        if (end == queue.length) end = 0;
        if (queue[end] != null) resize();
        queue[end++] = s;
    }

    public void pop() {
        if (front == end) {
            System.out.println("Queue is empty!");
            return;
        }
        queue[front++] = null;
    }

    public boolean isEmpty() {
        return front == end;
    }


    private void resize() {
        size *= 2;
        String[] newQueue = new String[size];
        int i = 0, newEnd;
        do {
            newQueue[i++] = queue[front++];
            if (front == queue.length) front = 0;
            newEnd = i;
        } while (front != end);
        front = 0;
        end = newEnd;
        queue = newQueue;
    }

    public int numberOfElements() {
        int max = queue.length;
        if (end == front && queue[front] == null) return 0;
        if (end == front) return max;
        if (end > front) return end - front;
        return max - end + front;
//        if (end >= front) {
//            if (queue[front] == null) return 0;
//            return end - front;
//        } else return max - end + front;
    }

    public String toString() {
        return Arrays.toString(queue) + " current size: " + queue.length + "\nnumber of elements: " + numberOfElements();
    }
}
