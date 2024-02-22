import java.awt.Color;

class FilledCircle extends Circle {
    private final Color color;

    FilledCircle(double radius, Color color) {
        super(radius);
        this.color = color;
    }

    FilledCircle fillColor(Color color) {
        return new FilledCircle(super.getRadius(), color);
    }

    public String toString() {
        return "filled " + super.toString() + ", color " + this.color;
    }
}
