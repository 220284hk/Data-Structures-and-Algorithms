package myImplementationsW2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private static final int HEAD = 1, TAIL = 0;
    private Item[] deque;
    private int head = 0, capacity = 1, tail = 0;
    private int itemCount = 0;

    // construct an empty deque
    public Deque() {
        deque = (Item[]) new Object[capacity];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return deque[head] == null;
    }

    private boolean isFull() {
        return size() == capacity;
    }

    //     return the number of items on the deque
    public int size() {
        return itemCount;
    }

    // add the item to the head
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            deque[head] = item;
            itemCount++;
            return;
        } else if (isFull()) {
            resize();
        }
        adjust(HEAD);
        deque[--head] = item;
        itemCount++;
    }

    private void resize() {
        if (size() == capacity)   // increasing size
            capacity *= 2;
        else
            capacity /= 2;

        Item[] newDeck = (Item[]) new Object[capacity];
        if (itemCount != 0) {
            for (int i = 0; i < itemCount; i++) {
                newDeck[i] = deque[head++];
                if (head == deque.length) head = 0;
            }
        }
        tail = itemCount - 1;

        head = 0;
        deque = newDeck;
    }

    public String toString() {
        return Arrays.toString(deque);
    }

    // add the item to the bBack
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            deque[tail] = item;
            itemCount++;
            return;
        } else if (isFull()) {
            resize();
        }
        adjust(TAIL);
        deque[++tail] = item;
        itemCount++;
    }

    // remove and return the item from the fFront
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item val = deque[head];
        deque[head] = null;
        itemCount--;
        if (size() == 0) return val;
        head++;
        adjust(HEAD);
        if (size() < capacity / 4) resize();
        return val;
    }

    // remove and return the item from the bBack
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item val = deque[tail];
        deque[tail] = null;
        itemCount--;
        if (size() == 0) return val;
        tail--;
        adjust(TAIL);
        if (size() < capacity / 4) resize();
        return val;
    }

    private void adjust(int p) {
        if (p == HEAD) { // HEAD
            if (head == 0) head = capacity;
            else if (head == capacity) head = 0;
        } else if (p == TAIL) { // TAIL
            if (tail == -1) tail = capacity - 1;
            else if (tail == capacity - 1) tail = -1;
        }
    }

    // return an iterator over items in order from head to tail
    public Iterator<Item> iterator() {

        return new Iterator<Item>() {
            private int index = head;
            private int count = 0;

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public boolean hasNext() {
                return count < size();
            }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                count++;
                if (index == capacity) index = 0;
                return deque[index++];
            }
        };
    }

    public static void main(String[] args) {
        //    Intentionally blank
    }
}

