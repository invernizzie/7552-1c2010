package model.edgedetection.impl;

import model.edgedetection.ProximityPointSet;
import model.edgedetection.Stroke;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Esteban I. Invernizzi
 *         Date 13/05/2010
 */
public class SimpleProximityPointSet implements ProximityPointSet {

    Set<Point> points = new HashSet<Point>();

    public Point popNearestTo(Stroke stroke, double maxDistance) {
        Point result = peekNearestTo(stroke, maxDistance);
        if (result != null)
            points.remove(result);
        return result;
    }

    public Point popNearestTo(Stroke stroke) {
        Point result = peekNearestTo(stroke);
        points.remove(result);
        return result;
    }

    public Point peekNearestTo(Stroke stroke, double maxDistance) {
        Point result = peekNearestTo(stroke);
        if ((result == null) || (stroke.distanceTo(result) > maxDistance))
            return null;
        return result;
    }

    public Point peekNearestTo(Stroke stroke) {
        Point nearestToFirst = peekNearestTo(stroke.getFirstPoint());
        Point nearestToLast = peekNearestTo(stroke.getLastPoint());
        if (nearestToFirst == null)
            return nearestToLast;
        if (absDistance(nearestToFirst, stroke.getFirstPoint()) < absDistance(nearestToLast, stroke.getLastPoint()))
            return nearestToFirst;
        return nearestToLast;
    }

    public Point popNearestTo(Point point, double maxDistance) {
        Point result = peekNearestTo(point);
        if (result == null)
            return null;
        if (absDistance(result, point) > maxDistance )
            return null;
        else {
            points.remove(result);
            return result;
        }
    }

    public Point popNearestTo(Point point) {
        Point result = peekNearestTo(point);
        if (result != null)
            points.remove(result);
        return result;
    }

    public Point peekNearestTo(Point point) {
        if (point == null)
            return null;
        
        Point result = null;
        double minDistance = Double.MAX_VALUE;
        for (Point setPoint: points)
            if (absDistance(setPoint, point) < minDistance) {
                result = setPoint;
                minDistance = absDistance(setPoint, point);
            }
        return result;
    }

    public void add(Point point) {
        if (point != null)
            points.add(point);
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    private double absDistance(Point p1, Point p2) {
        return Math.abs(p1.distance(p2));
    }
}
