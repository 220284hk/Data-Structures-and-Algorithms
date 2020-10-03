package myImplementationsW1;

public class QuickFindUF {
    private int[] nodes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public void connect(int p, int q) {
        if (nodes[p] == nodes[q])
            return;
        else {
            StringBuilder sb = new StringBuilder();
            int val = nodes[p];
            for (int i = 0; i < 10; i++) {
                if (nodes[i] == val) {
                    nodes[i] = nodes[q];
                }
                sb.append(nodes[i] + " ");
            }
            System.out.println(sb.toString());
        }
    }

    public void isConnected(int p, int q) {
        System.out.println(nodes[p] == nodes[q]);
    }

}
