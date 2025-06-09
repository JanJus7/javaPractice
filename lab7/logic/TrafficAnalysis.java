package logic;

import java.util.*;
import java.util.stream.Collectors;
import model.*;

public class TrafficAnalysis {

    public static double averageWaitTime(List<Vehicle> vehicles, Class<?> vehicleClass) {
        return vehicles.stream()
                .filter(v -> v.getClass().equals(vehicleClass))
                .mapToInt(Vehicle::getWaitTime)
                .average()
                .orElse(0);
    }

    public static Map<String, TrafficStatus> intersectionStatuses(List<Intersection> intersections) {
        return intersections.stream()
                .flatMap(i -> i.getDirectionQueue().entrySet().stream()
                        .map(entry -> {
                            String direction = entry.getKey();
                            int count = entry.getValue();
                            TrafficStatus status;

                            if (count > 10) {
                                status = TrafficStatus.KOREK;
                            } else if (count > 5) {
                                status = TrafficStatus.WZMOZONY_RUCH;
                            } else {
                                status = TrafficStatus.NORMALNY;
                            }

                            return Map.entry(i.getId() + ":" + direction, status);
                        }))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, Map<Integer, Long>> vehicleCountByPriorityAndDirection(List<Intersection> intersections) {
        return intersections.stream()
                .flatMap(i -> i.getDirectionQueue().keySet().stream()
                        .map(direction -> Map.entry(
                                i.getId() + ":" + direction,
                                i.getVehicles().stream()
                                        .filter(v -> v.getRoute().get(0).equals(i.getId() + ":" + direction))
                                        .collect(Collectors.groupingBy(Vehicle::getPriority, Collectors.counting())))))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, Integer> totalWaitTimeByIntersection(List<Vehicle> vehicles) {
        return vehicles.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getRoute().isEmpty()
                                ? "Intersection"
                                : v.getRoute().get(0).split(":")[0],
                        Collectors.summingInt(Vehicle::getWaitTime)));
    }

}
