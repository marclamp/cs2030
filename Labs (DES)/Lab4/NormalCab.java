import java.util.List;

class NormalCab extends Driver {
    private final ImList<Service> listOfServices = 
        new ImList<Service>(List.of(new JustRide(), new TakeACab()));

    NormalCab(String plateNum, int passWaitingTime) {
        super(plateNum, passWaitingTime);
    }

    @Override 
    public ImList<Service> getServices() {
        return this.listOfServices;
    }

    @Override
    public String toString() {
        return super.toString() + "NormalCab";
    }
}
