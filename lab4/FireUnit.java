package lab4;

public class FireUnit extends RescueUnit {

    public FireUnit(String baseLocation) {
        super(baseLocation, Type.FIRE);
    }

    @Override
    public void dispatchTo(String location) {
        System.out.println("Firefighters going to: " + location);
        setCurrentLocation(location);
        setStatus(Status.DISPATCHED);
    }

    @Override
    public void returnToBase() {
        System.out.println("Firefighters returning to base: " + getBaseLocation());
        setCurrentLocation(getBaseLocation());
        setStatus(Status.AVAILABLE);
    }
}
