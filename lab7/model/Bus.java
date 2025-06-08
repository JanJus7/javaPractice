package model;

import java.util.List;

public class Bus extends Vehicle {
    public Bus(String id, List<String> route) {
        super(id, 2, route);
    }
}
