package model;

import java.util.List;

public abstract class Vehicle extends TrafficElement {
    protected int priority;
    protected int waitTime;
    protected List<String> route;

    public Vehicle(String id, int priority, List<String> route) {
        super(id);
        this.priority = priority;
        this.waitTime = 0;
        this.route = route;
    }

    public int getPriority() {
        return priority;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public List<String> getRoute() {
        return route;
    }

    public void incrementWaitTime() {
        waitTime++;
    }

    @Override
    public void updateStatus() {
        incrementWaitTime();
    }
}
