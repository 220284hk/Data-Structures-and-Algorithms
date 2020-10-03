//package myImplementations;
//
//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdStats;
//
//public class PercolationStats {
//    private static final double CONFIDENCE_95 = 1.96;
//    private double[] results;
//    private int trials;
//
//    // perform independent trials on an n-by-n grid
//    public PercolationStats(int n, int trials) {
//        if (n <= 0 || trials <= 0)
//            throw new IllegalArgumentException("n and trials must be greater than 0 ");
//        this.trials = trials;
//        int i = 0;
//        results = new double[trials];
//        while (i < trials) {
//            Percolation percolation = new Percolation(n);
//            while (!percolation.percolates()) {
//                int x = StdRandom.uniform(1, n + 1), y = StdRandom.uniform(1, n + 1);
//                percolation.open(x, y);
//            }
//            results[i] += percolation.numberOfOpenSites() / (double) (n * n);
//            i++;
//        }
//    }
//
//    public static void main(String[] args) {
//        int n = Integer.parseInt(args[0]);
//        int trials = Integer.parseInt(args[1]);
//        PercolationStats percolationStats = new PercolationStats(n, trials);
//
//        System.out.println("Mean = " + percolationStats.mean());
//        System.out.println("Stddev = " + percolationStats.stddev());
//        System.out.println(String.format("95%% confidence interval = [%f, %f]",
//                percolationStats.confidenceLo(),
//                percolationStats.confidenceHi()));
//
//    }
//
//    // sample mean of percolation threshold
//    public double mean() {
//        return StdStats.mean(results);
//        // return average;
//    }
//
//
//    // sample standard deviation of percolation threshold
//    public double stddev() {
//        return StdStats.stddev(results);
//    }
//
//    // low endpoint of 95% confidence interval
//    public double confidenceLo() {
//        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(trials);
//    }
//
//    // high endpoint of 95% confidence interval
//    public double confidenceHi() {
//        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(trials);
//    }
//
//    // test client (see below)
//
//}
