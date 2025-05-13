package lab4;

public class AmbulanceUnit extends RescueUnit {

    public AmbulanceUnit(String baseLocation) {
        super(baseLocation, Type.AMBULANCE);
    }

    @Override
    public void dispatchTo(String location) {
        System.out.println("Ambulance going to: " + location);
        setCurrentLocation(location);
        setStatus(Status.DISPATCHED);
    }

    @Override
    public void returnToBase() {
        System.out.println("Ambulance returning to base: " + getBaseLocation());
        setCurrentLocation(getBaseLocation());
        setStatus(Status.AVAILABLE);
    }
}
