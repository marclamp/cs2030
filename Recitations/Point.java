import java.util.Objects;

class Point {
    // properties
    final double x;
    final double y;

    // constructor 
    Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    // method 
    double distanceTo(Point otherpoint){
        double dispX = this.x - otherpoint.x;
        double dispY = this.y - otherpoint.y;
        double distance = Math.sqrt(dispX * dispX + dispY * dispY);
        return distance;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Point p) {
            return (this.x == p.x) && (this.y == p.y);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }
}
