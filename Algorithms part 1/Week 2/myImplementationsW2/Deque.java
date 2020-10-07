package myImplementationsW2;

import queues.Dequev3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Item[] deck;
    private int front = 0, capacity = 1, back = 1;
    private int itemCount = 0;

    // construct an empty deque
    @SuppressWarnings({"MoveFieldAssignmentToInitializer", "unchecked"})
    public Deque() {
        deck = (Item[]) new Object[capacity];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return deck[front] == null;
    }

    //     return the number of items on the deque
    public int size() {
        return itemCount;
    }
    // Though this method is not that difficult to implement, it encapsulates a lot of the difficulty in
    // working out required to see whether or not certain methods are operable. Nevermind, it was difficult initially.

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size() == capacity) {
            resize();   //checks for full. Why can't we just use front == back? ;)
        }
        if (deck[front] == null) {
            deck[front] = item;
        } else {
            if (front == 0) front = capacity;
            deck[--front] = item;
        }
        if (front == back) back = back - 1;        // In the case that they meet
        itemCount++;
    }

    private void resize() {
        if (size() == capacity)   // increasing size
            capacity *= 2;
        else
            capacity /= 2;

        int i = 0;
        Item[] newDeck = (Item[]) new Object[capacity];
        if (itemCount != 0) {
            for (i = 0; i < itemCount; i++) {
                newDeck[i] = deck[front++];
                if (front == deck.length) front = 0;
            }
        }
        back = itemCount;
        front = 0;
        deck = newDeck;
    }

    public String toString() {
        return Arrays.toString(deck);
    }

    // add the item to the bBack
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            deck[front] = item;
            itemCount++;
            return;
        } else if (size() == capacity) {
            resize();
        }

        itemCount++;
        deck[back++] = item;
        if (back == capacity) back = 0;
    }

    // remove and return the item from the fFront
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item val = deck[front];
        deck[front++] = null;
        if (front == capacity) front = 0;
        if (size() <= capacity / 4) resize();
        itemCount--;
        return val;
    }

    // remove and return the item from the bBack
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        if (back == 0) back = capacity;
        Item val = deck[--back];
        deck[back] = null;
        itemCount--;
        if (size() <= capacity / 4) resize();
        if (back == front) back += 1;
        return val;
    }


    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {

        return new Iterator<Item>() {
            int index = front;
            int count = 0;

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public boolean hasNext() {
                return count < size();
            }

            public Item next() {
//                if (index == back) throw new NoSuchElementException();
                if (!hasNext()) throw new NoSuchElementException();
                count++;
                if (index == capacity) index = 0;
                return deck[index++];
            }
        };
    }


    public static <T> void print(Dequev3<T> deck) {
        if (deck.isEmpty()) {
            System.out.println("Empty");
            return;
        }
        System.out.print("front ");
        for (T t : deck) {
            System.out.print(t + " ");
        }
        System.out.print("back");
    }

    public static void main(String[] args) {
//        Dequev3<Integer> deck = new Dequev3<>();
        test1();
        test2();
        test4();
    }

    public static void test1() {
        System.out.println("Test 1: addFirst -> removeFirst -> addFirst -> removeLast");
        Dequev3<Integer> deck = new Dequev3<>();
        deck.addFirst(1);
        assert deck.size() == 1;
        assert deck.removeFirst() == 1;
        deck.addFirst(1);
        assert deck.removeLast() == 1;
        assert deck.size() == 0;
        System.out.println("First test passed");
    }

    public static void test2() {
        System.out.println("Test 2: addFirst loop -> removeLast loop");
        Dequev3<Integer> deck = new Dequev3<>();
        for (int i = 0; i < 100; i++) {
            assert deck.size() == i;
            deck.addFirst(i);
        }

        for (int i = 0; i < 100; i++) {
            assert deck.removeLast() == i;
        }
        System.out.println("Second test passed");
        System.out.println("Test 3: addLast loop -> removeFirst loop");

        for (int i = 0; i < 100; i++) {
            assert deck.size() == i;
            deck.addLast(i);
        }

        for (int i = 0; i < 100; i++) {
            assert deck.removeFirst() == i;
        }
        System.out.println("Third test passed");
    }

    public static void test4() {
        System.out.println("Test 4: mix method calls");
        Dequev3<Integer> deck = new Dequev3<>();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0)
                deck.addFirst(i);
            else
                deck.addLast(i);
        }
        for (int i = 49; i >= 0; i--) {
            assert deck.removeFirst() == i * 2;
            assert deck.removeLast() == i * 2 + 1;
        }
        System.out.println("Test 4 passed!");
    }

}


//9 8 ... 0..100 .. 99..91
