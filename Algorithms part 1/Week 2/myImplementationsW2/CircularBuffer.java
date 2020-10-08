package myImplementationsW2;

import java.util.Iterator;

public class CircularBuffer<T> implements Iterable<T> {
    private T[] array;
    private int p1, p2;

    public CircularBuffer() {
        array = (T[]) new Object[10];
        p1 = 0;
        p2 = 0;
    }

    public void add(T t) {
        // need to add resize when p1 == p2 & p1 == null;
        array[p2++] = t;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HEAD -> ");
        for (int i = p1; i < p2; i++) {
            if (i == array.length) i = 0;
            sb.append(array[i]).append(" -> ");
        }
        sb.append(" END ");
        return sb.toString();
    }

    public void load() {
        array[p1++] = null;
    }

    public Iterator<T> iterator() {
        return new ReverseBufferIterator();
    }

    private class ReverseBufferIterator implements Iterator<T> {
        private int pointer = p1;

        public boolean hasNext() {
            return pointer != p2;
        }

        public T next() {
            if (pointer == array.length) pointer = 0;
            return array[pointer++];
        }
    }

    public static void main(String[] args) {
        CircularBuffer<Integer> stringBuffer = new CircularBuffer<>();
        for (int i = 5; i < 9; i++) {
            stringBuffer.add(i);
        }

        System.out.println(stringBuffer);

        for (int i = 0; i < 4; i++) {
            stringBuffer.add(i);
        }

        System.out.println(stringBuffer);

        stringBuffer.load();

        System.out.println(stringBuffer);
        stringBuffer.add(3);
        stringBuffer.add(3);
        stringBuffer.add(3);
        stringBuffer.add(3);
        stringBuffer.add(3);

        for (Integer x : stringBuffer) {
            System.out.println(x);
        }
    }
}
