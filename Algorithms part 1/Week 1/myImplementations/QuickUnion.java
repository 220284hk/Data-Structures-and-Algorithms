package myImplementations;

public class QuickUnion {
    private int[] valueAt = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
//    private int[] valueAt = {0, 1, 2, 4, 4, 5, 6, 7, 8, 9};

    public void connect(int p, int q) {
        valueAt[root(p)] = root(q);
    }

    public int root(int p) {
        while (p != valueAt[p]) {
            p = valueAt[p];
        }
        return p;
    }

    public void isConnected(int p, int q) {
        System.out.println(String.format("root1 is: %d\nroot2 is: %d\n%b", root(p), root(q), root(p) == root(q)));
    }

//    public void printState() {
//        HashSet set = new HashSet();
//        set.addAll(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
//
//        for (int i = 0; i < 10; i++) {
//            if (set.contains(i)) {
//                StringBuilder sb = new StringBuilder();
//                int index = i;
//                do {
//                    sb.append(index + " -> ");
//                    index = valueAt[index];
//                } while (valueAt[index] != index);
//                System.out.println(sb.toString());
//            }
//        }
//    }

}
