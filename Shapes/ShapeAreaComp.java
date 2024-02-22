import java.util.Comparator;

class ShapeAreaComp implements Comparator<Shape> {
    public int compare(Shape s1, Shape s2) {
        double diff = s1.getArea() - s2.getArea();
        if (diff < 0) {
            return -1;
        } else if (diff > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}


