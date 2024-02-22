class Square implements Shape, Scalable {
    private final double size;

    Square(double size) {
        this.size = size;
    }

    @Override 
    public double getArea() {
        return this.size * this.size;
    }

    @Override
    public Square scale(double factor) {
        return new Square(this.size * factor);
    }

    @Override
    public String toString() {
        return "Square " + this.size + " x " + this.size;
    }
}

