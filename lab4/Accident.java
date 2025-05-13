package lab4;

import java.time.LocalDateTime;

public class Accident {
    private static int counter = 0;
    private final int id;
    private final LocalDateTime timestamp;
    private final String location;
    private boolean isActive;
    private final AccidentType type;
    private final int peopleInjured;

    public enum AccidentType {
        COLLISION,
        FIRE,
        EXPLOSION,
        FLOOD,
        EARTHQUAKE
    }

    public Accident(LocalDateTime timestamp, String location, AccidentType type, int peopleInjured) {
        this.id = ++counter;
        this.timestamp = timestamp;
        this.location = location;
        this.type = type;
        this.peopleInjured = peopleInjured;
        this.isActive = true;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getLocation() {
        return location;
    }

    public boolean isActive() {
        return isActive;
    }

    public AccidentType getType() {
        return type;
    }

    public int getPeopleInjured() {
        return peopleInjured;
    }

    public void setInactive() {
        this.isActive = false;
    }

    @Override
    public String toString() {
        return "[Accident #" + id + "] " + type + " at " + location + " with " + peopleInjured + " injured";
    }
}
