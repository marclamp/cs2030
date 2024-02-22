import java.util.List;

class Vector2D {
    private final List<Double> coord;

    // private final double x;
    // private final double y;

    Vector2D(double x, double y) {
        this.coord = List.of(x, y);
        // this.x = x;
        // this.y = y;
    }
    private double getX() {
        return this.coord.get(0);
        // return this.x;
    }
    
    private double getY() {
        return this.coord.get(1);
        // return this.y;
    }
    
    Vector2D add(Vector2D v) {
        Vector2D newVector = new Vector2D(
        this.getX() + v.getX(),
        this.getY() + v.getY());
        // line A
        return newVector;
    }
    
    @Override
    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }
}