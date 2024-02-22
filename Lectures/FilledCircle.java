import java.awt.Color;

class FilledCircle extends Circle {
    private final Color color;

    FilledCircle(Point center, double radius, Color color) {
        super(center, radius);
        this.color = color;
    }

    FilledCircle fillColor(Color color) {
        return new FilledCircle(super.center, super.radius, color);
    }

    public String toString() {
        return "filled " + super.toString() + ", color " + this.color;
    }
}
