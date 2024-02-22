class Cuboid implements Shape3D {
    private final double height;
    private final double width;
    private final double length;

    public Cuboid(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public double volume() {
        return this.height * this.width * this.length;
    }

    @Override
    public String toString() {
        return "cuboid [" + String.format("%.2f", this.height) + " x " +
                String.format("%.2f", this.width) + " x "  +
                String.format("%.2f", this.length) + "]";
    }
}
