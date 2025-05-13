package lab4;

public class PoliceUnit extends RescueUnit {

    public PoliceUnit(String baseLocation) {
        super(baseLocation, Type.POLICE);
    }

    @Override
    public void dispatchTo(String location) {
        System.out.println("Police going to: " + location);
        setCurrentLocation(location);
        setStatus(Status.DISPATCHED);
    }

    @Override
    public void returnToBase() {
        System.out.println("Police returning to base: " + getBaseLocation());
        setCurrentLocation(getBaseLocation());
        setStatus(Status.AVAILABLE);
    }
}
