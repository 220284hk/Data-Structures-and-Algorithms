/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class KdTree {
    private Node root;
    private boolean horizontal;
    private int size;

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
    }

    public void insert(Point2D p) {
        // StdDraw.setPenRadius(0.01);
        // p.draw();
        // StdDraw.setPenRadius(0.001);
        if (root == null) {
            // StdDraw.setPenRadius(0.015);
            // p.draw();
            // StdDraw.setPenRadius(0.001);
            root = new Node();
            root.p = p;
            root.rect = new RectHV(0, 0, 1, 1);
            size = 1;
        }
        else {
            horizontal = true;
            ins(p, root);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public boolean contains(Point2D p) {
        if (size == 0) return false;
        horizontal = true;
        return contains(p, root);
    }

    private boolean contains(Point2D p, Node parent) {
        if (parent == null) return false;
        if (parent.p.equals(p)) return true;
        if (horizontal) {
            if (p.x() <= parent.p.x()) return contains(p, parent.lb);
            horizontal = false;
            return contains(p, parent.rt);
        }
        else {
            if (p.y() <= parent.p.y()) return contains(p, parent.lb);
            horizontal = true;
            return contains(p, parent.rt);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        Set<Point2D> x = new HashSet<>();

        if (rect.contains(root.p)) x.add(root.p);
        traverseTree(x, root, rect);

        return x;
    }

    private void traverseTree(Set<Point2D> x, Node parent, RectHV rect) {
        if (parent.lb != null && parent.lb.rect.intersects(rect)) {
            if (rect.contains(parent.lb.p)) {
                x.add(parent.lb.p);
            }
            traverseTree(x, parent.lb, rect);
        }
        if (parent.rt != null && parent.rt.rect.intersects(rect)) {
            if (rect.contains(parent.rt.p)) {
                x.add(parent.rt.p);
            }
            traverseTree(x, parent.rt, rect);
        }
    }

    public void draw() {
        StdDraw.setPenRadius(0.001);
        draw(root, Boolean.FALSE);
    }

    private void draw(Node parent, Boolean h) {
        if (parent == null) return;
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.008);
        parent.p.draw();
        StdDraw.setPenRadius(0.001);
        // StdDraw.pause(3000);
        if (h) {
            StdDraw.setPenColor(Color.BLUE);
            RectHV t = new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(),
                    parent.p.y());
            t.draw();
            draw(parent.lb, Boolean.FALSE);
            draw(parent.rt, Boolean.FALSE);
        }
        else {
            StdDraw.setPenColor(Color.RED);
            RectHV t = new RectHV(parent.p.x(), parent.rect.ymin(), parent.p.x(),
                    parent.rect.ymax());
            t.draw();
            draw(parent.lb, Boolean.TRUE);
            draw(parent.rt, Boolean.TRUE);
        }
    }

    // private void draw(RectHV rect) {
    //     if (horizontal) {
    //         StdDraw.setPenColor(StdDraw.BLUE);
    //         rect.draw();
    //         StdDraw.setPenColor(StdDraw.BLACK);
    //     }
    //     else {
    //         StdDraw.setPenColor(StdDraw.RED);
    //         rect.draw();
    //         StdDraw.setPenColor(StdDraw.BLACK);
    //     }
    //
    // }

    private void ins(Point2D p, Node parent) {
        if (parent.p.equals(p)) {
            System.out.println("Already contains the point " + p);
            return;
        }
        if (horizontal) {
            if (p.x() <= parent.p.x()) {    // Not 100% sure about this equal
                if (parent.lb == null) {
                    parent.lb = new Node();
                    parent.lb.p = p;
                    parent.lb.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(),
                            parent.p.y(), parent.rect.ymax());
                    size += 1;
                    // draw(new RectHV(parent.rect.xmin(), p.y(), parent.p.x(),
                    //                 p.y())); // this is for debugging
                }
                else {
                    horizontal = false;
                    ins(p, parent.lb);
                }
            }
            else {
                if (parent.rt == null) {
                    parent.rt = new Node();
                    parent.rt.p = p;
                    parent.rt.rect = new RectHV(parent.p.x(), parent.rect.ymin(),
                            parent.rect.xmax(), parent.rect.ymax());
                    // draw(new RectHV(parent.p.x(), p.y(), parent.rect.xmax(), p.y()));
                    size += 1;
                }
                else {
                    horizontal = false;
                    ins(p, parent.rt);
                }
            }
        }
        else {
            if (p.y() <= parent.p.y()) {    // Not 100% sure about this equal
                if (parent.lb == null) {
                    parent.lb = new Node();
                    parent.lb.p = p;
                    parent.lb.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(),
                            parent.rect.xmax(), parent.p.y());
                    // draw(new RectHV(p.x(), parent.rect.ymin(), p.x(),
                    //                 parent.p.y())); // this is for debugging
                    size += 1;
                }
                else {
                    horizontal = true;
                    ins(p, parent.lb);
                }
            }
            else {
                if (parent.rt == null) {
                    parent.rt = new Node();
                    parent.rt.p = p;
                    parent.rt.rect = new RectHV(parent.rect.xmin(), parent.p.y(),
                            parent.rect.xmax(), parent.rect.ymax());
                    // draw(new RectHV(p.x(), parent.p.y(), p.x(), parent.rect.ymax()));
                    size += 1;
                }
                else {
                    horizontal = true;
                    ins(p, parent.rt);
                }
            }
        }
    }

    // drawing vertical in red, horizontal in blue

    public static void main(String[] args) {
        KdTree t = new KdTree();
        assert (t.size == 0);
        assert (!t.contains(new Point2D(0.2, 0.2)));
        t.insert(new Point2D(0.2, 0.2));
        assert (!t.contains(new Point2D(0.6, 0.6)));
        t.insert(new Point2D(0.2, 0.2));
        t.insert(new Point2D(0.4, 0.4));
        assert (t.size() == 2);
        t.insert(new Point2D(0.6, 0.6));
        assert (t.contains(new Point2D(0.6, 0.6)));
        t.insert(new Point2D(0.8, 0.8));
        t.insert(new Point2D(0.1, 0.8));
        t.insert(new Point2D(0.3, 0.7));
        t.insert(new Point2D(0.55, 0.35));
        t.insert(new Point2D(0.7, 0.16));
        t.insert(new Point2D(0.5, 0.5));
        t.insert(new Point2D(0.55, 0.55));
        t.insert(new Point2D(0.6, 0.6));
        t.insert(new Point2D(0.65, 0.65));
        assert (t.size() == 11);
        System.out.println("hi");
        t.draw();

        Iterator<Point2D> it = t.range(new RectHV(0.55, 0.55, 0.55, 0.55)).iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
        // for (int i = 0; i < 10; i++) {
        //     Point2D p = new Point2D(StdRandom.uniform(0.0, 1.0), StdRandom.uniform(0.0, 1.0));
        //     t.insert(p);
        // }
    }
}
