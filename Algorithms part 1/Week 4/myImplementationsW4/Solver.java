import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private Stack<Board> solutionStack = new Stack<>();
    /*
    What do I want?
    MinPQ<SearchNode> -> MinPQ<> containing all searchNodes from first to last (including non solutions);
    Stack which contains the solution;
     */

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        // initialising
        MinPQ<SearchNode> priorityQueue = new MinPQ<>();
        priorityQueue.insert(new SearchNode(initial, null));
        MinPQ<SearchNode> twinPriorityQueue = new MinPQ<>();
        twinPriorityQueue.insert(new SearchNode(initial.twin(), null));
        SearchNode sn, twinSn;
        MinPQ<SearchNode> solutionQueue = new MinPQ<>();

        do {
            sn = priorityQueue.delMin();
            solutionQueue.insert(sn);

            twinSn = twinPriorityQueue.delMin();

            for (Board neighbs : sn.board.neighbors()) {
                if (sn.previous == null || !sn.previous.board.equals(neighbs)) {
                    priorityQueue
                            .insert(new SearchNode(neighbs, sn));

                }
            }

            for (Board neighbs : twinSn.board.neighbors()) {
                if (twinSn.previous == null || !twinSn.previous.board.equals(neighbs)) {
                    twinPriorityQueue
                            .insert(new SearchNode(neighbs, twinSn));

                }
            }

        } while (!sn.board.isGoal() && !twinSn.board.isGoal());

        // Insoluble
        if (twinSn.board.isGoal()) {
            solutionStack = new Stack<>();
            return;
        }

        // solutionStack.push(sn.board);

        // Initial is solution
        // if (sn.previous != null) solutionStack.push(sn.previous);

        while (sn != null) {
            solutionStack.push(sn.board);
            sn = sn.previous;
        }


    }

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final SearchNode previous;
        private final int moves;
        private final int manhattan;

        public SearchNode(Board board, SearchNode previous) {
            this.board = board;
            this.previous = previous;
            this.manhattan = board.manhattan();
            if (previous == null) this.moves = 0;
            else
                this.moves = previous.moves + 1;
        }

        public int compareTo(SearchNode a) {
            int x = Integer.compare(this.moves + this.manhattan, a.moves + a.manhattan);
            if (x != 0) {
                return x;
            }
            return Integer.compare(this.board.hamming(), a.board.hamming());
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return !solutionStack.isEmpty();
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return solutionStack.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (isSolvable()) return solutionStack;
        return null;
    }

    // test client (see below)
    public static void main(String[] args) {
        // Intentionally blank
    }
}
