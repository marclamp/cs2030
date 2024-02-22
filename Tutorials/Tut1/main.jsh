double EPSILON = 1e-15;

boolean circleContainsPoint(Circle c, Point p) {
    return p.distanceTo(c.getCentre()) < c.getRadius() + EPSILON;
}

int findMaxDiscCoverage(List<Point> points) {
    int maxDiscCoverage = 0;
    int numOfPoints = points.size();

    for (int i = 0; i < numOfPoints - 1; i++) {
        for (int j = i + 1; j < numOfPoints; j++) {
            Point p = points.get(i);
            Point q = points.get(j);
            double distPQ = p.distanceTo(q);

            if (distPQ < 2.0 + EPSILON && distPQ > 0) {
                Circle c = createUnitCircle(p, q);
                int coverage = 0;
                for (Point point : points) {
                    if (circleContainsPoint(c, point)) {
                        coverage = coverage + 1;
                    }
                }
                if (coverage > maxDiscCoverage) {
                    maxDiscCoverage = coverage;
                }
            }
        }
    }
    return maxDiscCoverage;
}

    Circle createUnitCircle(Point p, Point q) {
    Point midpoint = p.midPoint(q);
    double theta = p.angleTo(q) + Math.PI / 2;
    double distanceMQ = midpoint.distanceTo(q);
    double distanceMC = Math.sqrt(1 - Math.pow(distanceMQ, 2));
    Point centre = midpoint.moveTo(theta, distanceMC);
    return new Circle(centre, 1);
}


