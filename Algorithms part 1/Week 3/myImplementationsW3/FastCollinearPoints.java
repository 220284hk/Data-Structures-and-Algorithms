package myImplementationsW3;/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Quick;

import java.util.Arrays;

public class FastCollinearPoints {
    private int count = 0;
    // private int pointCount = 0;
    private Point[] myPointsA = new Point[2];
    private LineSegment[] array = new LineSegment[1];

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }

        Point[] myPoints = points.clone();
        // Quick.sort(myPoints);
        //
        // for (int i = 0; i < myPoints.length - 1; i++) {
        //     if (myPoints[i].slopeTo(myPoints[i + 1]) == Double.NEGATIVE_INFINITY) {
        //         throw new IllegalArgumentException();
        //     }
        // }

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Double[] slopeArray = new Double[points.length];
            for (int j = 0; j < slopeArray.length; j++) {
                slopeArray[j] = p.slopeTo(myPoints[j]);
            }

            // sorting the slopes array (& lines array) so that SLOPES are in increasing order
            quickSort(slopeArray, myPoints);
            if (slopeArray.length > 1 && slopeArray[1] == Double.NEGATIVE_INFINITY)
                throw new IllegalArgumentException();

            for (int w = 0; w < slopeArray.length; w++) {
                int consec = 2;

                while (w < slopeArray.length - 1 && slopeArray[w].equals(slopeArray[w + 1])) {
                    consec++;
                    w++;
                }

                if (consec >= 4) {
                    // Check if it already exists in the array
                    Point[] pA = new Point[consec];
                    int k = 0;
                    while (consec - k - 1 > 0) {
                        pA[k] = myPoints[w - k];
                        k++;
                    }
                    pA[k] = p;
                    Quick.sort(pA);

                    if (!inArray(pA[0], pA[pA.length - 1])) {
                        LineSegment temp = new LineSegment(pA[0], pA[pA.length - 1]);

                        array[count] = temp;
                        myPointsA[count * 2] = pA[0];
                        myPointsA[count * 2 + 1] = pA[pA.length - 1];
                        count++;
                        if (array.length == count) {
                            resizeArray();
                        }

                    }
                }

            }
        }
        cleanArray();
    }

    private boolean inArray(Point a, Point b) {
        if (count == 0) return false;
        for (int i = 0; i < count * 2; i += 2) {
            if (myPointsA[i].equals(a) && myPointsA[i + 1].equals(b)) return true;
        }
        return false;
    }

    private void cleanArray() {
        LineSegment[] temp = new LineSegment[count];
        for (int i = 0; i < count; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }


    // private boolean inArray(LineSegment temp, LineSegment[] a) {
    //     for (int i = 0; i < count; i++) {
    //         if (temp.toString().equals(a[i].toString())) return true;
    //     }
    //     return false;
    // }

    private void resizeArray() {
        LineSegment[] a = new LineSegment[count * 2];
        for (int i = 0; i < count; i++) {
            a[i] = array[i];
        }
        array = a;

        int x = count * 4;
        Point[] b = new Point[x];
        for (int i = 0; i < count * 2; i++) {
            b[i] = myPointsA[i];
        }
        myPointsA = b;
        // System.out.println(Arrays.toString(b));
    }

    public int numberOfSegments() {
        return count;
    }        // the number of line segments

    public LineSegment[] segments() {
        return array.clone();
    }           // the line segments

    public static void main(String[] args) {
        // // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        //
        // // draw the points
        // StdDraw.enableDoubleBuffering();
        // StdDraw.setXscale(0, 32768);
        // StdDraw.setYscale(0, 32768);
        // for (Point p : points) {
        //     p.draw();
        // }
        // StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        // for (LineSegment segment : collinear.segments()) {
        //     StdOut.println(segment);
        //     segment.draw();
        // }
        // StdDraw.show();
        //
        System.out.println(collinear.numberOfSegments());
        System.out.println(Arrays.toString(collinear.segments()));

    }

    private static void quickSort(Comparable[] a, Point[] pA) {
        sort(a, 0, a.length - 1, pA);
    }

    private static void sort(Comparable[] a, int lo, int hi, Point[] pA) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi, pA);
        sort(a, lo, j - 1, pA);
        sort(a, j + 1, hi, pA);
    }

    private static int partition(Comparable[] a, int lo, int hi, Point[] pA) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo]))
                if (i == hi) break;
            while (less(a[lo], a[--j]))
                if (j == lo) break;

            if (i >= j) break;
            exch(a, i, j);
            exch(pA, i, j);
        }
        exch(a, lo, j);
        exch(pA, lo, j);
        return j;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable comparable, Comparable comparable1) {
        return comparable.compareTo(comparable1) < 0;
    }

}
