/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private Node root;
    private boolean horizontal;

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
    }

    public void insert(Point2D p) {
        StdDraw.setPenRadius(0.01);
        p.draw();
        StdDraw.setPenRadius(0.001);
        if (root == null) {
            StdDraw.setPenRadius(0.015);
            p.draw();
            StdDraw.setPenRadius(0.001);
            root = new Node();
            root.p = p;
            root.rect = new RectHV(0, 0, 1, 1);
            RectHV temp = new RectHV(p.x(), 0, p.x(), 1);
            StdDraw.setPenColor(StdDraw.RED);
            temp.draw();
            StdDraw.setPenColor(StdDraw.BLACK);
        }
        else {
            horizontal = true;
            ins(p, root);
        }
    }

    private void draw(RectHV rect) {
        if (horizontal) {
            StdDraw.setPenColor(StdDraw.BLUE);
            rect.draw();
            StdDraw.setPenColor(StdDraw.BLACK);
        }
        else {
            StdDraw.setPenColor(StdDraw.RED);
            rect.draw();
            StdDraw.setPenColor(StdDraw.BLACK);
        }

    }

    private void ins(Point2D p, Node parent) {
        if (horizontal) {
            if (p.x() <= parent.p.x()) {    // Not 100% sure about this equal
                if (parent.lb == null) {
                    parent.lb = new Node();
                    parent.lb.p = p;
                    parent.lb.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(),
                                                parent.p.y(), parent.rect.ymax());
                    draw(new RectHV(parent.rect.xmin(), p.y(), parent.p.x(),
                                    p.y())); // this is for debugging
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
                    draw(new RectHV(parent.p.x(), p.y(), parent.rect.xmax(), p.y()));
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
                    draw(new RectHV(p.x(), parent.rect.ymin(), p.x(),
                                    parent.p.y())); // this is for debugging
                    // StdDraw.setPenColor(StdDraw.BLUE);
                    // temp.draw();
                    // StdDraw.setPenColor(StdDraw.BLACK);
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
                    draw(new RectHV(p.x(), parent.p.y(), p.x(), parent.rect.ymax()));
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
        t.insert(new Point2D(0.2, 0.2));
        t.insert(new Point2D(0.4, 0.4));
        t.insert(new Point2D(0.6, 0.6));
        t.insert(new Point2D(0.8, 0.8));

        t.insert(new Point2D(0.1, 0.8));
        t.insert(new Point2D(0.3, 0.7));
        t.insert(new Point2D(0.55, 0.35));
        t.insert(new Point2D(0.7, 0.16));

        t.insert(new Point2D(0.5, 0.5));
        t.insert(new Point2D(0.55, 0.55));
        t.insert(new Point2D(0.6, 0.6));
        t.insert(new Point2D(0.65, 0.65));

        // for (int i = 0; i < 10; i++) {
        //     Point2D p = new Point2D(StdRandom.uniform(0.0, 1.0), StdRandom.uniform(0.0, 1.0));
        //     t.insert(p);
        // }
    }
}
