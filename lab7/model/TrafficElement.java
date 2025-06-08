package model;

public abstract class TrafficElement {
    protected String id;
    protected TrafficStatus status;

    public TrafficElement(String id) {
        this.id = id;
        this.status = TrafficStatus.NORMALNY;
    }

    public abstract void updateStatus();

    public String getId() { return id; }
    public TrafficStatus getStatus() { return status; }
    public void setStatus(TrafficStatus status) { this.status = status; }
}
