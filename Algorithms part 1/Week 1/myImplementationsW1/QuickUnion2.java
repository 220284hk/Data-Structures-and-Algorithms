package myImplementationsW1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickUnion2 {
    private int[] valueAt = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int[] sizeAt = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private List<int[]> logFile = new ArrayList<>();
    private int computationCount = 0;

    public int getComputationCount() {
        return computationCount;
    }

    public int earliestConnectivity() {
        boolean notConnected;
        System.out.println(logFile.size() + " unique connections provided in logFile");
        for (int i = 0; i < logFile.size(); i++) {
            computationCount++;
            connect(logFile.get(i)[0], logFile.get(i)[1]);      // worst case: connect at m calls (provided m is less than n-1 C2). m * 2n + 5
            notConnected = Arrays.stream(sizeAt).noneMatch(it -> it == 10); //n array accesses
//            notConnected = Arrays.stream(sizeAt).anyMatch(it -> it == 10 || (it != 10 && it != 0) ); //2 array accesses and once all 10
            if (!notConnected) {
                System.out.println("Fully connected at " + (i + 1));
                return i;
            }
        }
        System.out.println("Not connected");
        return 0;
    }

    public void generateLogfile() {
//        logFile.add(new int[]{1, 9});
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
//                if (i == 5 & j == 6) continue;
                int[] t = new int[]{i, j};
                logFile.add(t);
            }
        }
        logFile.add(new int[]{5, 6});
        logFile.add(new int[]{1, 9});

    }

    public void connect(int p, int q) {
        updateWeight(p, q);
    }

    private void updateWeight(int p, int q) {
        int rootP = root(p), rootQ = root(q); //2n array accesses
        int sizeP = sizeAt[rootP], sizeQ = sizeAt[rootQ]; //2 array accesses
        if (sizeQ >= sizeP) {
            sizeAt[rootP] = 0;
            valueAt[rootP] = rootQ;
            sizeAt[rootQ] += sizeP;             //3 array accesses,  2n + 5 in total
        } else {
            sizeAt[rootQ] = 0;
            valueAt[rootQ] = rootP;
            sizeAt[rootP] += sizeQ;
        }
        computationCount++;
    }

    public int root(int p) {
        while (p != valueAt[p]) {
            valueAt[p] = valueAt[valueAt[p]];
            p = valueAt[p];
            computationCount++;
        }
        return p;
    }

    @Override
    public String toString() {
        System.out.print("Connections: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(valueAt[i] + " ");
        }

        System.out.print("\nWeights: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(sizeAt[i] + " ");
        }
        return super.toString();
    }

}
