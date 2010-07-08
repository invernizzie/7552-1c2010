package model.util;

import model.edgedetection.impl.IdentifiablePoint;

import java.awt.Point;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 29/05/2010
 */
public class PointUtils {

    public static double absDistance(Point p1, Point p2) {
        return Math.abs(p1.distance(p2));
    }

    public static Point difference(Point reference, Point point) {
        return new IdentifiablePoint(
                point.x - reference.x,
                point.y - reference.y );
    }

    public static double dotProduct(Point p1, Point p2) {
        if ((p1 == null) || (p2 == null))
            return 0;
        return p1.x * p2.x + p1.y * p2.y;
    }

    public static Point interpolate(int x, Point p1, Point p2) {

        Point leftmost = (p1.x < p2.x) ? p1 : p2;
        Point rightmost = (leftmost == p2) ? p1 : p2;

        if (leftmost.x == rightmost.x)
            return new IdentifiablePoint(x, (leftmost.y + rightmost.y) / 2);

        double slope = ((double)(rightmost.y - leftmost.y)) / (rightmost.x - leftmost.x);
        return new IdentifiablePoint(x, (int)((x - leftmost.x) * slope + leftmost.y));
    }
}
