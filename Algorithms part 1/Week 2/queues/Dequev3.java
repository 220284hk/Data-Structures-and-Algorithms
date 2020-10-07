package queues;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Item[] deck;
    private int front = 0, capacity = 2, back = 1;
    public int itemCount = 0;

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
//        if (isEmpty()) return 0;
//        if (front < back) return back - front;
//        if (front == back && deck[front] != null) return deck.length;
//        if (front == back && isEmpty()) return 0;
//        return deck.length - front + back;
        return itemCount;
    }
    // Though this method is not that difficult to implement, it encapsulates a lot of the difficulty in
    // working out required to see whether or not certain methods are operable.

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
            do {
                newDeck[i++] = deck[front++];
                if (front == deck.length) front = 0;
            } while (front != back);
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
        if (size() == capacity) resize();
        itemCount++;
        deck[back++] = item;
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
        itemCount--;
        if (back == 0) back = capacity;
        Item val = deck[--back];
        deck[back] = null;
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


    public static <T> void print(Deque<T> deck) {
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

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deck = new Deque<>();
        for (int i = 0; i < 4; i++) {
            deck.addFirst(i);
            System.out.println(deck);
            System.out.println("Front is: " + deck.front + " Back is: " + deck.back);
        }

        for (Integer i : deck) {
            System.out.println(i);
        }
        System.out.println("////");

        for (int i = 0; i < 4; i++) {
            System.out.println(deck);
            deck.removeFirst();
            System.out.println(deck);
        }
        print(deck);
    }

}
