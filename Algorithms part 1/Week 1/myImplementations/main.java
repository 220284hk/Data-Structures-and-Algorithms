package myImplementations;

public class main {

    public static void main(String[] args) {
        QuickUnion2 qu = new QuickUnion2();
        qu.generateLogfile();
//        qu.connect(9, 1);
        qu.earliestConnectivity();
        System.out.println(qu);
        System.out.println(qu.getComputationCount());
    }
}
