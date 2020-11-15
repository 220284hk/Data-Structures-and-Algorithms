/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.HashSet;
import java.util.Set;

public class PointSET {
    private Set<Point2D> set;

    public PointSET() {
        set = new HashSet<Point2D>();
    }                       // construct an empty set of points

    // is the set empty?
    public boolean isEmpty() {
        return set.isEmpty();
    }

    // number of points in the set
    public int size() {
        return set.size();
    }

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
        return closestPoint;

    }

    public static void main(String[] args) {

    }                 // unit testing of the methods (optional)
}
