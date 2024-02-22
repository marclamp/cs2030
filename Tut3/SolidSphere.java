class SolidSphere extends Sphere implements Solid {
    private final double density;

    public SolidSphere(double radius, double density) {
        super(radius);
        this.density = density;
    }

    public double mass() {
        return new SolidImpl(this, this.density).mass();
    }

    @Override
    public String toString() {
        return "solid-" + super.toString() + 
            " with a mass of " + String.format("%.2f", this.mass());
    }
}
