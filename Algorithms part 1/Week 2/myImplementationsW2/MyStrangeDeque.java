package myImplementationsW2;

import java.util.Iterator;

public class MyStrangeDeque<Item> implements Iterable<Item> {
    private Item[] deck;
    private int fFront = 0, fBack = 0, bFront = 0, bBack = 0;

    // construct an empty deque
    @SuppressWarnings({"MoveFieldAssignmentToInitializer", "unchecked"})
    public MyStrangeDeque() {
        deck = (Item[]) new Object[1];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return deck[fFront] == null && deck[bFront] == null;
    }

    // return the number of items on the deque
//    public int size() {
//        return fFront + deck.length - 1 - bBack;
//    }

    // add the item to the front
    public void addFirst(Item item) {
        if (bToB()) {
            deck[fBack] = item;
            fBack = 0;
        }
        deck[fBack++] = item;
    }

    private boolean bToB() {
        return fBack + 1 == bBack;
    }

    public String toString() {
        return deck[0].toString();
    }

    // add the item to the bBack
    public void addLast(Item item) {
        deck[bBack--] = item;
    }

    // remove and return the item from the fFront
    public Item removeFirst() {
        Item val = deck[fFront];
        deck[fFront++] = null;
        return val;
    }

    // remove and return the item from the bBack
    public Item removeLast() {
        Item val = deck[bFront];
        deck[bFront--] = null;
        return val;
    }


    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            public boolean hasNext() {
                return false;
            }

            public Item next() {
                return null;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
