/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;

public class KdTree {
    private Node root;
    private boolean horizontal;
    private int size = 0;
    private Point2D closestPoint;

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) {
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

    private void ins(Point2D p, Node parent) {
        if (parent.p.equals(p)) { return; }
        if (horizontal) {
            if (p.x() <= parent.p.x()) {
                if (parent.lb == null) {
                    parent.lb = new Node();
                    parent.lb.p = p;
                    parent.lb.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(),
                            parent.p.x(), parent.rect.ymax());
                    size += 1;
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
                    size += 1;
                }
                else {
                    horizontal = false;
                    ins(p, parent.rt);
                }
            }
        }
        else {
            if (p.y() <= parent.p.y()) {
                if (parent.lb == null) {
                    parent.lb = new Node();
                    parent.lb.p = p;
                    parent.lb.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(),
                            parent.rect.xmax(), parent.p.y());

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
                    size += 1;
                }
                else {
                    horizontal = true;
                    ins(p, parent.rt);
                }
            }
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (size == 0) return false;
        horizontal = true;
        return contains(p, root);
    }

    private boolean contains(Point2D p, Node parent) {
        if (parent == null) return false;
        if (parent.p.equals(p)) return true;
        if (horizontal) {
            horizontal = false;
            if (p.x() <= parent.p.x()) return contains(p, parent.lb);
            return contains(p, parent.rt);
        }
        else {
            horizontal = true;
            if (p.y() <= parent.p.y()) return contains(p, parent.lb);
            return contains(p, parent.rt);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        Queue<Point2D> x = new Queue<>();
        if (size == 0) return x;

        if (rect.contains(root.p)) x.enqueue(root.p);
        traverseTree(x, root, rect);

        return x;
    }

    private void traverseTree(Queue<Point2D> x, Node parent, RectHV rect) {
        if (parent.lb != null && parent.lb.rect.intersects(rect)) {
            if (rect.contains(parent.lb.p)) {
                x.enqueue(parent.lb.p);
            }
            traverseTree(x, parent.lb, rect);
        }
        if (parent.rt != null && parent.rt.rect.intersects(rect)) {
            if (rect.contains(parent.rt.p)) {
                x.enqueue(parent.rt.p);
            }
            traverseTree(x, parent.rt, rect);
        }
    }

    public void draw() {
        StdDraw.setPenRadius(0.001);
        draw(root, Boolean.FALSE);
    }

    private void draw(Node node, Boolean h) {
        if (node == null) return;
        // StdDraw.setPenColor(Color.BLACK);
        // StdDraw.setPenRadius(0.008);
        // node.p.draw();
        // StdDraw.setPenRadius(0.005);
        // StdDraw.pause(5000);
        if (h) {
            StdDraw.setPenColor(Color.BLUE);
            RectHV t = new RectHV(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
            t.draw();
            draw(node.lb, Boolean.FALSE);
            draw(node.rt, Boolean.FALSE);
        }
        else {
            StdDraw.setPenColor(Color.RED);
            RectHV t = new RectHV(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
            t.draw();
            draw(node.lb, Boolean.TRUE);
            draw(node.rt, Boolean.TRUE);
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (size() == 0) return null;
        closestPoint = root.p;

        closer(root, p);

        return closestPoint;
    }

    private void closer(Node parent, Point2D p) {
        if (parent == null) return;
        // System.out.println("I have been queried: " + parent.p);
        if (parent.p.distanceSquaredTo(p) < closestPoint.distanceSquaredTo(p)) {
            closestPoint = parent.p;
        }

        if (parent.lb != null && parent.rt != null) {
            double diff = parent.lb.rect.distanceSquaredTo(p) - parent.rt.rect.distanceSquaredTo(p);

            if (diff < 0) {
                closer(parent.lb, p);

                if (parent.rt.rect.distanceSquaredTo(p) < closestPoint.distanceSquaredTo(p)) {
                    closer(parent.rt, p);
                }
            }
            else {
                closer(parent.rt, p);

                if (parent.lb.rect.distanceSquaredTo(p) < closestPoint.distanceSquaredTo(p)) {
                    closer(parent.lb, p);
                }
            }
        }
        else if (parent.lb == null) {
            if (parent.rt != null && parent.rt.rect.distanceSquaredTo(p) < closestPoint.distanceSquaredTo(p)) {
                closer(parent.rt, p);
            }
        }
        else {
            if (parent.lb.rect.distanceSquaredTo(p) < closestPoint.distanceSquaredTo(p)) {
                closer(parent.lb, p);
            }
        }
    }


    public static void main(String[] args) {
        // Intentionally blank
    }
}
