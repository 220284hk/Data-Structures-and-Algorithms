package myImplementationsW4;

public class MyBST<Key extends Comparable<Key>, Value> {
    private int size;
    private Node<Key, Value> head;

    public MyBST() {
        head = new Node(null, null);
        size = 0;
    }

    public void put(Key key, Value value) {
        if (head.key == null) {
            head.key = key;
            head.value = value;
            size++;
        } else {
            put(head, key, value);
        }


//        else if (c(head.key, key) == 0) {
//            head.key = key;
//            head.value = value;
//            System.out.println("OVERWRITTEN");
//        } else if (c(head.key, key) > 0) {
//            put(head.left, key, value);
//        } else { // c() < 0
//            put(head.right, key, value);
//        }
    }

    private void put(Node<Key, Value> parent, Key key, Value value) {
        if (c(parent.key, key) == 0) {
            parent.key = key;
            parent.value = value;
        } else if (c(parent.key, key) > 0) {
            if (parent.left == null) {
                parent.left = new Node(key, value);
                size++;
            } else {
                put(parent.left, key, value);
            }
        } else { // c() < 0
            if (parent.right == null) {
                parent.right = new Node(key, value);
                size++;

            } else {
                put(parent.right, key, value);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node p = head;
        traversal(head, sb);
        return sb.toString();
    }

    private void traversal(Node parent, StringBuilder sb) {
        sb.append(parent.key).append(" ");
        if (parent.left != null) {
            traversal(parent.left, sb);
        } else {
            sb.append("\n");
        }

        if (parent.right != null) {
            traversal(parent.right, sb);
        } else {
            sb.append("\n");
        }

    }

    //
    private int c(Key key1, Key key2) {
        return key1.compareTo(key2);
    }

//    public Value get(Key key) {}

    public boolean contains(Key key) {
        return false;
    }

    public void delete(Key key) {
    }

//    public Iterable<Key> iterator() {}

    private class Node<Key, Value> {
        private Node left, right;
        private Key key;
        private Value value;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

    }

    public static void main(String[] args) {
    }
}
