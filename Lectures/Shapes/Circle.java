class Circle implements Shape, Scalable {
    private final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public Circle scale(double factor) {
        return new Circle(this.radius * factor);
    }

    @Override
    public String toString() {
        return "Circle with radius " + this.radius;
    }

    public boolean equals(Circle circle) {
        System.out.println("Running equals(Circle) method");
        return circle.radius == radius;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Running equals(Object) method");
        if (obj == this) {
            return true;
        } else if (obj instanceof Circle circle) {
            return circle.radius == this.radius;
        } else {
            return false;
        }
    }
}
