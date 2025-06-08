package logic;

import java.util.*;
import java.util.stream.Collectors;
import model.*;

public class TrafficOptimizer {

    public static String optimizeTrafficLight(Intersection intersection, Map<String, TrafficLight> lights) {
        List<Vehicle> vehicles = intersection.getVehicles();

        Map<String, Integer> directionScores = vehicles.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getRoute().get(0).split(":")[1],
                        Collectors.summingInt(v -> v.getPriority() + v.getWaitTime())));

        List<String> allDirections = List.of("N", "S", "E", "W");
        for (String dir : allDirections) {
            directionScores.putIfAbsent(dir, 0);
        }

        String bestDirection = directionScores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        lights.forEach((dir, light) -> {
            if (dir.equals(bestDirection)) {
                light.setColour(Colours.ZIELONY);
            } else {
                light.setColour(Colours.CZERWONY);
            }
        });

        return bestDirection;
    }

    public static int calculateGreenTime(String direction, Intersection intersection) {
        return intersection.getVehicles().stream()
                .filter(v -> v.getRoute().get(0).equals(intersection.getId() + ":" + direction))
                .mapToInt(v -> v.getPriority() + v.getWaitTime())
                .sum() + 5;
    }

}