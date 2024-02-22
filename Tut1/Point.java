class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point midPoint(Point p) {
        double newX = (this.x + p.x) / 2;
        double newY = (this.y + p.y) / 2;

        return new Point(newX, newY);
    }

    public double angleTo(Point p) {
        double theta = Math.atan2((p.y - this.y), (p.x - this.x));
        return theta;
    }
    
    public Point moveTo(double theta, double d) {
        double newX = (this.x + d * Math.cos(theta));
        double newY = (this.y + d * Math.sin(theta));

        return new Point(newX, newY);
    }

    public double distanceTo(Point p) {
        double dispX = this.x - p.x;
        double dispY = this.y - p.y;
        double distance = Math.sqrt(dispX * dispX + dispY * dispY);
        return distance;
    }

    @Override
    public String toString() {
        return String.format("point (%.3f, %.3f)", this.x, this.y);
    }

}
