package myImplementationsW3;/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private int count = 0;
    private LineSegment[] array = new LineSegment[1];

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }

        Point[] myPoints = points.clone();

        Quick.sort(myPoints);

        for (int i = 0; i < myPoints.length - 1; i++) {
            if (myPoints[i].slopeTo(myPoints[i + 1]) == Double.NEGATIVE_INFINITY) {
                throw new IllegalArgumentException();
            }
        }

        int size = myPoints.length;
        for (int i1 = 0; i1 < size; i1++) {
            for (int i2 = i1 + 1; i2 < size; i2++) {
                for (int i3 = i2 + 1; i3 < size; i3++) {
                    for (int i4 = i3 + 1; i4 < size; i4++) {
                        if (myPoints[i1].slopeTo(myPoints[i2]) == myPoints[i1].slopeTo(myPoints[i3])
                                && myPoints[i1].slopeTo(myPoints[i3]) == myPoints[i1]
                                .slopeTo(myPoints[i4])) {
                            LineSegment a = new LineSegment(myPoints[i1], myPoints[i4]);
                            if (array.length == count) {
                                resizeArray();
                            }

                            array[count++] = a;
                        }
                    }
                }
            }
        }
        cleanArray();
    }

    private void cleanArray() {
        LineSegment[] temp = new LineSegment[count];
        for (int i = 0; i < count; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    private void resizeArray() {
        LineSegment[] temp = new LineSegment[count * 2];
        for (int i = 0; i < count; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public int numberOfSegments() {
        return count;
    }        // the number of line segments

    public LineSegment[] segments() {
        return array.clone();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        BruteCollinearPoints bp = new BruteCollinearPoints(points);


        for (LineSegment segment : bp.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();


        // Point[] points2 = new Point[4];
        // points2[0] = new Point(1, 1);
        // points2[1] = new Point(1, 2);
        // points2[2] = new Point(1, 3);
        // points2[3] = new Point(3, 1);
        //
        // System.out.println(Arrays.toString(points));
        // BruteCollinearPoints bp2 = new BruteCollinearPoints(points2);
        // System.out.println(Arrays.toString(bp2.array));
        // System.out.println(Arrays.toString(bp2.array));


    }
}
