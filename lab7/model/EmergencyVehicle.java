package model;

import java.util.List;

class EmergencyVehicle extends Vehicle {
    public EmergencyVehicle(String id, List<String> route) {
        super(id, 3, route);
    }
}
