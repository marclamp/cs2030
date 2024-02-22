class Circle {
    private final Point centre;
    private final double radius;

    Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    public double distanceFromCenter(Point p) {
        return p.distanceTo(this.centre);
    }

    public boolean contains(Point p) {
        return (this.distanceFromCenter(p) < this.radius);
    }

    @Override
    public String toString() {
        return "circle of radius " + String.format("%.1f", this.radius) + 
            " centred at " + this.centre.toString();
    }
}
