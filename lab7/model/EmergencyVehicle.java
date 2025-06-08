package model;

import java.util.List;

public class EmergencyVehicle extends Vehicle {
    public EmergencyVehicle(String id, List<String> route) {
        super(id, 3, route);
    }
}
