package model;

import java.util.List;

class Car extends Vehicle {
    public Car(String id, List<String> route) {
        super(id, 1, route);
    }
}
