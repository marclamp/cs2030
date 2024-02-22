class Circle {
    final Point center;
    protected final double radius;

    // constructor
    Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }
    
    boolean contains(Point p) {
      return this.center.distanceTo(p) < this.radius;
    }

    Circle scale(double factor) {
        return new Circle(this.center, this.radius * factor);
    }

    @Override
    public String toString() {
        return "Circle centered at " + this.center.toString() +  " with radius " + this.radius;
    }
}

