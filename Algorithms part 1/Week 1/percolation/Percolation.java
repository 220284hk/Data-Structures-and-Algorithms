// /* *****************************************************************************
//  *  Name:    Ada Lovelace
//  *  NetID:   alovelace
//  *  Precept: P00
//  *
//  *  Description:  Prints 'Hello, World' to the terminal window.
//  *                By tradition, this is everyone's first program.
//  *                Prof. Brian Kernighan initiated this tradition in 1974.
//  *
//  **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int length;
    private int openSites;
    private final WeightedQuickUnionUF uf;
    private boolean[] isOpenAt;
    private boolean testBegins = false;

    // // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        length = n;
        uf = new WeightedQuickUnionUF(length * length + 2);
        isOpenAt = new boolean[length * length + 2];
        isOpenAt[0] = true;
        isOpenAt[length * length + 1] = true;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || col <= 0 || row > length || col > length)
            throw new IllegalArgumentException("Must be between 1 and n");

        int loc = xyTo1D(row, col);

        if (isOpenAt[loc]) return;
        openSites++;
        isOpenAt[loc] = true;

        boolean[] sides = new boolean[] { true, true, true, true };

        if (row == 1) {
            sides[0] = false;
            uf.union(0, loc);
        }
        if (loc % length == 1) {
            sides[3] = false;
        }
        if (loc % length == 0) {
            sides[1] = false;
        }
        if (row == length) {
            sides[2] = false;
            testBegins = true;
        }

        // checking for neighbour connections :) and connecting
        if (sides[0] && isOpenAt[loc - length]) uf.union(loc, loc - length);
        if (sides[1] && isOpenAt[loc + 1]) uf.union(loc, loc + 1);
        if (sides[2] && isOpenAt[loc + length]) uf.union(loc, loc + length);
        if (sides[3] && isOpenAt[loc - 1]) uf.union(loc, loc - 1);

        if (testBegins) {
            for (int x = length * (length - 1) + 1; x < length * length + 1; x++) {
                if (isOpenAt[x] && uf.find(x) == uf.find(0)) uf.union(x, length * length + 1);
            }
        }
    }

    private int xyTo1D(int x, int y) {
        return (x - 1) * length + y;
    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 || row > length || col > length)
            throw new IllegalArgumentException("Must be between 1 and n");
        return isOpenAt[xyTo1D(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0 || row > length || col > length)
            throw new IllegalArgumentException("Must be between 1 and n");
        return uf.find(xyTo1D(row, col)) == uf.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // // does the system percolate?
    public boolean percolates() {
        // return uf.connected(0, length * length + 1);
        return uf.find(length * length + 1) == uf.find(0);

    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                percolation.open(i, j);
                System.out.println(percolation.percolates());
            }
        }
    }
    // sort of works
    // public void open(int row, int col) {
    //     int loc = xyTo1D(row, col);
    //     openSites++;
    //     if (row <= 0 || col <= 0 || row > N || col > N)
    //         throw new IndexOutOfBoundsException("Must be between 1 and N");
    //     isOpenAt[loc] = true;
    //     boolean[] sides = new boolean[] { true, true, true, true };
    //
    //     if (row == 1) {
    //         sides[0] = false;
    //         uf.union(0, loc);
    //     }
    //     if (loc % N == 1) {
    //         sides[3] = false;
    //     }
    //     if (loc % N == 0) {
    //         sides[1] = false;
    //     }
    //     if (row == N) {
    //         sides[2] = false;
    //         if (uf.find(0) == uf.find(loc)) {
    //             uf.union(N * N + 1, loc);
    //         }
    //     }
    //     else if (N > 1 && row == N - 1 && isFull(row, col)) {
    //         uf.union(N * N + 1, loc + N);
    //     }
    //
    //     // checking for neighbour connections :) and connecting
    //     if (sides[0] && isOpenAt[loc - N]) uf.union(loc, loc - N);
    //     if (sides[1] && isOpenAt[loc + 1]) uf.union(loc, loc + 1);
    //     if (sides[2] && isOpenAt[loc + N]) uf.union(loc, loc + N);
    //     if (sides[3] && isOpenAt[loc - 1]) uf.union(loc, loc - 1);
    //
    // }

    // public void open(int row, int col) {
    //     int loc = xyTo1D(row, col);
    //     openSites++;
    //     if (row <= 0 || col <= 0 || row > N || col > N)
    //         throw new IndexOutOfBoundsException("Must be between 1 and N");
    //     isOpenAt[loc] = true;
    //     // if neighbours are open, we should add this 'node' to the node of 'full' boxes
    //
    //     // neighbours are edge cases
    //     // neighbours are NOT edge cases
    //     if (isEdge(row, col)) {
    //         if (row == 1) {
    //             uf.union(0, loc);
    //         }
    //         // Literally, edge cases
    //         else if (row == N) { // don't REALLY need to check sides as
    //             // that would imply it already percolates...
    //             if (col == 1) {
    //                 if (uf.connected(0, loc - N) || uf.connected(0, loc + 1)) {
    //                     uf.union(0, loc);
    //                 }
    //             }
    //             else if (col == N) {
    //                 if (uf.connected(0, loc - N) || uf.connected(0, loc - 1)) {
    //                     uf.union(0, loc);
    //                 }
    //             }
    //             else {
    //
    //             }
    //
    //         }
    //         else if (loc % N == 1) {        // location is first col
    //
    //         }
    //         else if (loc % N
    //                 == 0) {        // location is last col (must connect to last Node AND check for fullness)
    //
    //         }
    //     }
    //     else {
    //         // for row, col the neighbours are going to be (row-1, col), (row+1, col), (row, col-1), (row, col+1)
    //         if (uf.connected(0, loc - N) || uf.connected(0, loc + N) || uf.connected(0, loc - 1)
    //                 || uf.connected(0, loc + 1)) {
    //             uf.union(0, loc);
    //         }
    //     }
    // }
}

