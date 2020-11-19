/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.Set;

public class PointSET {
    private Set<Point2D> set;

    public PointSET() {
        set = new HashSet<Point2D>();
    }                       // construct an empty set of points

    // is the set empty?
    public boolean isEmpty() { return set.isEmpty(); }

    // number of points in the set
    public int size() { return set.size(); }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        set.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return set.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        StdDraw.setPenRadius(0.01);
        for (Point2D p : set) {
            p.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        Set<Point2D> x = new HashSet<>();
        for (Point2D p : set) {
            if (rect.contains(p)) x.add(p);
        }
        return x;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (set.isEmpty()) return null;
        Point2D closestPoint = null;
        double minDistance = 10;
        for (Point2D point : set) {
            if (closestPoint == null || point.distanceTo(p) < minDistance) {
                closestPoint = point;
                minDistance = closestPoint.distanceTo(p);
            }

        }
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.RED);
        closestPoint.draw();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        return closestPoint;

    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        StdDraw.setPenRadius(0.01);
        PointSET ps = new PointSET();

        for (int i = 0; i < 30; i++) {
            ps.insert(new Point2D(StdRandom.uniform(0.0, 1.0), StdRandom.uniform(0.0, 1.0)));
        }

        // Testing contains
        Point2D t1 = new Point2D(0.5, 0.5);
        StdDraw.setPenColor(StdDraw.GREEN);
        t1.draw();
        System.out.println("ps.contains(t1): " + ps.contains(t1));

        // Testing nearest
        System.out.println(ps.nearest(t1));
        System.out.println("ps.nearest(t1): " + ps.nearest(t1));

        // Testing range
        RectHV rect = new RectHV(0.25, 0.25, 0.75, 0.75);
        Set<Point2D> range = (Set<Point2D>) ps.range(rect);
        System.out.println("range: " + range);
        System.out.println("range size: " + range.size());

        // Testing size
        System.out.println("ps.size(): " + ps.size());

        ps.draw();
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.RED);
        rect.draw();


    }
}
























