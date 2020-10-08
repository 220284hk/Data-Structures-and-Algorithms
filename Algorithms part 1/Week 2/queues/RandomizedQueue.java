package queues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        StdRandom.uniform(capacity);
        int num = StdRandom.uniform(capacity);
        while (queue[num] != null) {
            num = StdRandom.uniform(capacity);
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
        int j = 0;
        for (int i = 0; i < currentSize; i++) {
            if (queue[i] != null) tempQueue[j++] = queue[i];
        }
        queue = tempQueue;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size() == 0) throw new NoSuchElementException();
        int num = StdRandom.uniform(capacity);
        while (queue[num] == null) {
            num = StdRandom.uniform(capacity);
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
        int num = StdRandom.uniform(capacity);
        while (queue[num] == null) {
            num = StdRandom.uniform(capacity);
        }
        return queue[num];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

        return new Iterator<Item>() {
            private boolean[] checked = new boolean[capacity];
            private int mCount = count;

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public boolean hasNext() {
                return mCount > 0;
            }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                int x = StdRandom.uniform(capacity);
                while (checked[x] || queue[x] == null) {
                    x = StdRandom.uniform(capacity);
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
        //    Intentionally empty
    }

}
