package model;

import java.util.List;

public class Car extends Vehicle {
    public Car(String id, List<String> route) {
        super(id, 1, route);
    }
}
