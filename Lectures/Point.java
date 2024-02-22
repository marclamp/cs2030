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

    // method
    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }
}
