class UnitCircle extends Circle {
    private static final double radius = 1.0;

    UnitCircle(Point centre) {
        super(centre, radius);
    }

    @Override
    UnitCircle scale(double factor) {
        return this;
    }
}
