import java.util.Random;
import java.util.stream.Stream;

class Mathematics {
    static double pow (double a, long b) {
        return Stream.<Double>generate(() -> a).limit(b).reduce((x,y) -> x * y).get();
    }

    static double seriesPi(long n) {
        return Stream.<Integer>iterate(1, x -> x + 1)
            .limit(n)
            .map(x -> {
                if (x % 2 == 0) {
                    return -4.0 / (2 * x - 1);
                } else {
                    return 4.0 / (2 * x - 1);
                }
            })
            .reduce((y,z) -> y + z)
            .get();
    }

    // returns a value between -1.0 and 1.0
    static double getRand() {
        Random rand = new Random();
        return rand.nextDouble() * 2.0 - 1.0;
    }

    static double approxPi(long n) {
        Circle unitCircle = new Circle(new Point(0, 0), 1.0);
        return Stream.iterate(1, x -> x + 1)
        .limit(n)
        .map(y -> new Point(getRand(), getRand()))
        .map(z -> unitCircle.contains(z))
        .map(a -> a == true ? 1.0 : 0.0)
        .reduce((x,y) -> x + y).get() / n * 4.0;
    }

    static double approxPiCorrect(long n) {
        Circle unitCircle = new Circle(new Point(0, 0), 1.0);
        return Stream.generate(() -> new Point(getRand(), getRand()))
        .limit(n)
        .filter(z -> unitCircle.contains(z))
        .count() * 4.0 / n;
    }


}

class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double getX() {
        return this.x;
    }

    double getY() {
        return this.y;
    }

    double distanceTo(Point otherpoint){
        double dispX = this.x - otherpoint.x;
        double dispY = this.y - otherpoint.y;
        double distance = Math.sqrt(dispX * dispX + dispY * dispY);
        return distance;
    }

    @Override
    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }
}

class Circle {
    private final Point center;
    private final double radius;

    // constructor
    Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public String toString() {
        return "Circle centered at " + this.center.toString() +  " with radius " + this.radius;
    }
    
    boolean contains(Point p) {
      return this.center.distanceTo(p) < this.radius;
    }

    public Circle scale(double factor) {
        return new Circle(this.center, this.radius * factor);
    }
}



