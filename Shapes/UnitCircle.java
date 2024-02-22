class UnitCircle extends Circle {
    private static final double radius = 1.0;

    UnitCircle() {
        super(radius);
    }

    @Override
    public UnitCircle scale(double factor) {
        return this;
    }
}
