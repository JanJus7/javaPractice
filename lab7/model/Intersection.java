package model;

import java.util.*;
import java.util.stream.Collectors;

public class Intersection extends TrafficElement {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final Map<String, Integer> directionQueue = new HashMap<>();

    public Intersection(String id) {
        super(id);
        directionQueue.put("N", 0);
        directionQueue.put("S", 0);
        directionQueue.put("E", 0);
        directionQueue.put("W", 0);
    }

    public void addVehicle(Vehicle v, String direction) {
        vehicles.add(v);
        directionQueue.put(direction, directionQueue.get(direction) + 1);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public Map<String, Integer> getDirectionQueue() {
        return directionQueue;
    }

    @Override
    public void updateStatus() {
        Map<String, Long> counts = vehicles.stream()
                .filter(v -> !v.getRoute().isEmpty())
                .map(v -> v.getRoute().get(0).split(":")[1])
                .collect(Collectors.groupingBy(d -> d, Collectors.counting()));

        long max = counts.values().stream().mapToLong(Long::longValue).max().orElse(0);

        if (max > 24) {
            status = TrafficStatus.KOREK;
        } else if (max > 10 && max <= 24) {
            status = TrafficStatus.WZMOZONY_RUCH;
        } else if (max <= 10) {
            status = TrafficStatus.NORMALNY;
        }
    }
}
