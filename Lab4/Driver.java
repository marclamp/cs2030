abstract class Driver implements Comparable<Driver> {
    private final String plateNum;
    private final int passWaitingTime;

    Driver(String plateNum, int passWaitingTime) {
        this.plateNum = plateNum;
        this.passWaitingTime = passWaitingTime;
    }

    public abstract ImList<Service> getServices();

    @Override
    public int compareTo(Driver driver) {
        return this.passWaitingTime - driver.passWaitingTime;
    }

    @Override
    public String toString() {
        return this.plateNum + " (" + this.passWaitingTime + " mins away) ";
    }
}
