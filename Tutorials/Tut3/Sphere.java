class Sphere implements Shape3D {
    private final double radius;
    private static final double FOUR_THIRDS = 4.0 / 3.0;
    private static final int CUBE = 3;

    public Sphere(double radius) {
        this.radius = radius;
    }

    public double volume() {
        return this.FOUR_THIRDS * Math.PI * Math.pow(this.radius, CUBE);
    }

    @Override
    public String toString() {
        return "sphere [" + String.format("%.2f", this.radius) + "]";
    }
}
