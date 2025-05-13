package lab4;

import java.time.LocalDateTime;
import java.util.*;

public class Report {
    private static int counter = 0;
    private final int id;
    private final LocalDateTime timestamp;
    private final String location;
    private Status status;
    private final List<RescueUnitType> requiredUnits;
    private final int turnsToComplete;
    private int turnsSpent;
    private final List<RescueUnit> assignedUnits;
    private final int peopleNeedingHelp;
    private int peopleHelped;

    public enum Status {
        ACTIVE, INACTIVE
    }

    public enum RescueUnitType {
        POLICE, FIRE, AMBULANCE
    }

    public Report(LocalDateTime timestamp, String location, List<RescueUnitType> requiredUnits,
                  int turnsToComplete, int peopleNeedingHelp) {
        this.id = ++counter;
        this.timestamp = timestamp;
        this.location = location;
        this.status = Status.ACTIVE;
        this.requiredUnits = requiredUnits;
        this.turnsToComplete = turnsToComplete;
        this.turnsSpent = 0;
        this.assignedUnits = new ArrayList<>();
        this.peopleNeedingHelp = peopleNeedingHelp;
        this.peopleHelped = 0;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public Status getStatus() {
        return status;
    }

    public List<RescueUnitType> getRequiredUnits() {
        return requiredUnits;
    }

    public void assignUnit(RescueUnit unit) {
        assignedUnits.add(unit);
    }

    public int getTurnsSpent() {
        return turnsSpent;
    }

    public int getTurnsToComplete() {
        return turnsToComplete;
    }

    public int getPeopleNeedingHelp() {
        return peopleNeedingHelp;
    }

    public int getPeopleHelped() {
        return peopleHelped;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<RescueUnit> getAssignedUnits() {
        return assignedUnits;
    }

    public void update() {
        turnsSpent++;

        for (RescueUnit unit : assignedUnits) {
            if (unit instanceof AmbulanceUnit && peopleHelped < peopleNeedingHelp) {
                peopleHelped++;
            }
        }

        if (turnsSpent >= turnsToComplete && peopleHelped >= peopleNeedingHelp) {
            status = Status.INACTIVE;
        }
    }

    @Override
    public String toString() {
        return "[Report #" + id + "] to " + location + ", status: " + status + ", helped: " + peopleHelped + "/" + peopleNeedingHelp;
    }
}
