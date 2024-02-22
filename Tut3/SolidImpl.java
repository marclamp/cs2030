class SolidImpl implements Solid {
    private final Shape3D shape3D;
    private final double density;

    public SolidImpl(Shape3D shape3D, double density) {
        this.shape3D = shape3D;
        this.density = density;
    }

    public double volume() {
        return this.shape3D.volume();
    }

    public double mass() {
        return shape3D.volume() * this.density;
    }

    @Override
    public String toString() {
        return "SolidImpl";
    }
}
