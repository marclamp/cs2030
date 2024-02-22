class Rectangle implements Shape, Scalable {
    private final double width;
    private final double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override 
    public double getArea() {
        return width * height;
    }

    @Override
    public Rectangle scale(double factor) {
        return new Rectangle(this.width * factor, this.height * factor);
    }

    @Override
    public String toString() {
        return "Rectangle " + this.width + " x " + this.height;
    }
}
