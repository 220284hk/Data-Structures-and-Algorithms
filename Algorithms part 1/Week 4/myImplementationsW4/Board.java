/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class Board {
    private int[][] tiles;
    private int n, x, y, manhattan = -1;

    public Board(int[][] tiles) {
        setupFields(tiles);
        setupTilesAndSolution(tiles);
    }

    private void setupFields(int[][] t) {
        this.n = t.length;
        this.tiles = new int[n][n];
    }

    private void setupTilesAndSolution(int[][] t) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (t[i][j] == 0) {
                    x = j;
                    y = i;
                }
                this.tiles[i][j] = t[i][j];
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n).append("\n");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(" ").append(tiles[i][j]);
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    // // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int count = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i * n) + j + 1 != tiles[i][j]) count++;
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        if (manhattan == -1) {
            int count = 0;
            for (int puzzleBit = 1; puzzleBit < n * n; puzzleBit++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if ((i * n) + j + 1 == puzzleBit) {
                            count += findCoords(tiles, puzzleBit, i, j);
                        }
                    }
                }
            }
            manhattan = count;
        }
        return manhattan;
    }

    private int findCoords(int[][] t, int puzzleBit, int i, int j) {
        for (int w = 0; w < n; w++) {
            for (int q = 0; q < n; q++) {
                if (t[w][q] == puzzleBit) {
                    return Math.abs(i - w) + Math.abs(q - j);
                }
            }
        }
        return -1;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object ob) {
        if (this == ob) return true;
        if (ob == null) return false;
        if (ob.getClass() != this.getClass()) return false;

        Board that = (Board) ob;
        if (this.n != that.n || this.x != that.x || this.y != that.y) return false;

        return Arrays.deepEquals(that.tiles, this.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> q = new Queue<>();
        Board b;

        if (y != 0) {
            swap(x, y, x, y - 1);
            b = new Board(tiles);
            swap(x, y - 1, x, y);
            q.enqueue(b);
        }
        if (x != 0) {
            swap(x, y, x - 1, y);
            b = new Board(tiles);
            swap(x - 1, y, x, y);
            q.enqueue(b);
        }
        if (y != n - 1) {
            swap(x, y, x, y + 1);
            b = new Board(tiles);
            swap(x, y + 1, x, y);
            q.enqueue(b);
        }
        if (x != n - 1) {
            swap(x, y, x + 1, y);
            b = new Board(tiles);
            swap(x + 1, y, x, y);
            q.enqueue(b);
        }
        return q;

    }

    private void swap(int x1, int y1, int w, int z) {
        tiles[y1][x1] = tiles[z][w];
        tiles[z][w] = 0;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        if (n < 2) throw new IllegalArgumentException();

        Board b;
        if (!(x == 0 && y == 0) && !(x == 1 && y == 1)) {
            int temp = tiles[0][0];
            tiles[0][0] = tiles[1][1];
            tiles[1][1] = temp;
            b = new Board(tiles);
            tiles[1][1] = tiles[0][0];
            tiles[0][0] = temp;
        }
        else {
            int temp = tiles[0][1];
            tiles[0][1] = tiles[1][0];
            tiles[1][0] = temp;
            b = new Board(tiles);
            tiles[1][0] = tiles[0][1];
            tiles[0][1] = temp;
        }
        return b;
    }

    public static void main(String[] args) {
        // Intentionally blank
    }

}
