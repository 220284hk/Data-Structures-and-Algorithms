package myImplementationsW3; /******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    // private static final Comparator<Point> BY_COORDINATES = new ByCoords();
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y < that.y)
            return -1;
        else if (this.y > that.y) {
            return 1;
        } else {
            if (this.x < that.x) {
                return -1;
            } else if (this.x > that.x) {
                return 1;
            }
            return 0;
        }
    }

    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        // if (that == null) throw new NullPointerException();
        if (this.x != that.x && this.y != that.y) {
            return (1.0 * that.y - this.y) / (1.0 * that.x - this.x);
        } else if (this.x == that.x && this.y == that.y) {
            return Double.NEGATIVE_INFINITY;
        } else if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        } else {
            return +0.0;
        }
    }

    public Comparator<Point> slopeOrder() {
        Point p = this;
        return new Comparator<Point>() {
            public int compare(Point q, Point r) {
                double s1 = p.slopeTo(q);
                double s2 = p.slopeTo(r);

                if (s1 == s2) return 0;
                if (s1 - s2 > 0) return 1;
                return -1;
            }
        };
    }


    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        // Point[] array = new Point[10];
        //
        // for (int i = 0; i < 10; i++) {
        //     array[i] = new Point(StdRandom.uniform(100), StdRandom.uniform(100));
        // }
        // System.out.println("Before sort: " + Arrays.toString(array));

        // Arrays.sort(array, new Point(1,1).slopeOrder().compare());

        // System.out.println("After sort: " + Arrays.toString(array));
        /* YOUR CODE HERE


         */

        Point p = new Point(2, 5);
        Point q = new Point(2, 8);
        Point r = new Point(2, 4);
        //
        System.out.println(p.slopeOrder().compare(q, r));
        System.out.println(p.slopeTo(r));
        System.out.println(p.slopeTo(q));
        // System.out.println(p.slopeTo(q));
        // System.out.println(p.slopeTo(r));
    }
}

