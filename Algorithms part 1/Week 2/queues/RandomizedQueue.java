package queues;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int capacity = 1, count = 0;
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (count == capacity) resize();
        Random random = new Random();
        int num = random.nextInt(capacity);
        while (queue[num] != null) {
            num = random.nextInt(capacity);
        }
        queue[num] = item;
        count++;
    }

    private void resize() {
        int currentSize = capacity;
        if (count == capacity) {
            capacity *= 2;
        } else {
            capacity /= 2;
        }

        Item[] tempQueue = (Item[]) new Object[capacity];
        for (int i = 0, j = 0; i < currentSize; i++) {
            if (queue[i] != null) tempQueue[j++] = queue[i];
        }
        queue = tempQueue;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size() == 0) throw new NoSuchElementException();
        Random random = new Random();
        int num = random.nextInt(capacity);
        while (queue[num] == null) {
            num = random.nextInt(capacity);
        }
        Item val = queue[num];
        queue[num] = null;
        count--;
        if (count < capacity / 4) resize();
        return val;
    }

    public String toString() {
        return Arrays.toString(queue);
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size() == 0) throw new NoSuchElementException();
        Random random = new Random();
        int num = random.nextInt(capacity);
        while (queue[num] != null) {
            num = random.nextInt(capacity);
        }
        return queue[num];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private boolean[] checked = new boolean[capacity];
            private int mCount = count;

            public boolean hasNext() {
                return mCount > 0;
            }

            public Item next() {
                Random random = new Random();
                int x = random.nextInt(capacity);
                while (checked[x] || queue[x] == null) {
                    x = random.nextInt(capacity);
                    if (queue[x] == null) checked[x] = true;
                }
                checked[x] = true;
                mCount--;
                return queue[x];
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        System.out.println(rq);
        for (int i = 0; i < 100; i++) {
            rq.enqueue(i);
        }
        System.out.println(rq);
        Iterator<Integer> it1 = rq.iterator();
        Iterator<Integer> it2 = rq.iterator();

        StringBuilder sb1 = new StringBuilder();
        sb1.append("Iterator 1: ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Iterator 2: ");
        for (int i = 0; i < 10; i++) {
            sb1.append(", " + it1.next());
            sb2.append(", " + it2.next());
        }
        System.out.println(sb1.toString());
        System.out.println(sb2.toString());
    }

}
