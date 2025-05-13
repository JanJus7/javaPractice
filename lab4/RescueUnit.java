package lab4;

public abstract class RescueUnit {
    private final String baseLocation;
    private String currentLocation;
    private Status status;
    private final Type type;

    public enum Status {
        AVAILABLE, DISPATCHED, BUSY
    }

    public enum Type {
        POLICE,
        FIRE,
        AMBULANCE
    }

    public RescueUnit(String baseLocation, Type type) {
        this.baseLocation = baseLocation;
        this.currentLocation = baseLocation;
        this.status = Status.AVAILABLE;
        this.type = type;
    }    

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getBaseLocation() {
        return baseLocation;
    }

    public Type getType() {
        return type;
    }
    public abstract void dispatchTo(String location);
    public abstract void returnToBase();
}
