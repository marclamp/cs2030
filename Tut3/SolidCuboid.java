class SolidCuboid extends Cuboid implements Solid { 
    private final double density;

    public SolidCuboid(double height, double width, double length, double density) {
        super(height, width, length);
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
