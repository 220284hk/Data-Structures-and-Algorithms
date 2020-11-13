package myImplementationsW4;

public class MyST<Key, Value> {
    Key[] k;
    Value[] v;
    int count = 0;


    public MyST() {
        k = (Key[]) new Object[10];
        v = (Value[]) new Object[10];
    }

    void put(Key key, Value value) {
        k[count] = key;
        v[count] = value;
        count++;
    }

//    Value get(Key key) {
//        if (k)
//    }

    public boolean contains(Key key) {
        for (int i = 0; i < 10; i++) {
            if (k[i] == key) return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

}
