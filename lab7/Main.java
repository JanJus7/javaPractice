import java.util.*;
import logic.TrafficAnalysis;
import logic.TrafficOptimizer;
import model.*;

public class Main {
    public static void main(String[] args) {
        Intersection intersection = new Intersection("Intersection");
        Intersection intersection2 = new Intersection("Intersection2");
        Intersection intersection3 = new Intersection("Intersection3");
        List<Vehicle> vehicles = new ArrayList<>();

        Map<String, String> directionMap = Map.of(
                "N", "S",
                "S", "N",
                "E", "W",
                "W", "E");
        String[] entryDirections = { "S", "N", "E", "W" };

        for (int i = 1; i <= 87; i++) {
            List<String> route = new ArrayList<>();
            String entry = entryDirections[(i - 1) % 4];
            // String exit = directionMap.get(entry);
            switch (entry) {
                case "N" -> {
                    route.add("Intersection:S");
                    route.add("Intersection2:S");
                    Vehicle car = new Car("Car " + i, route);
                    vehicles.add(car);
                    intersection.addVehicle(car, "N");
                }
                case "S" -> {
                    route.add("Intersection2:N");
                    route.add("Intersection:N");
                    Vehicle car = new Car("Car " + i, route);
                    vehicles.add(car);
                    intersection2.addVehicle(car, "S");
                }
                case "W" -> {
                    route.add("Intersection3:E");
                    route.add("Intersection:E");
                    Vehicle car = new Car("Car " + i, route);
                    vehicles.add(car);
                    intersection3.addVehicle(car, "W");
                }
                case "E" -> {
                    route.add("Intersection:W");
                    Vehicle car = new Car("Car " + i, route);
                    vehicles.add(car);
                    intersection.addVehicle(car, "E");
                }
            }
        }

        for (int i = 1; i <= 10; i++) {
            List<String> route = new ArrayList<>();
            String entry = entryDirections[(i - 1) % 4];
            // String exit = directionMap.get(entry);
            switch (entry) {
                case "N" -> {
                    route.add("Intersection:S");
                    route.add("Intersection2:S");
                    Vehicle bus = new Bus("Bus " + i, route);
                    vehicles.add(bus);
                    intersection.addVehicle(bus, "N");
                }
                case "S" -> {
                    route.add("Intersection2:N");
                    route.add("Intersection:N");
                    Vehicle bus = new Bus("Bus " + i, route);
                    vehicles.add(bus);
                    intersection2.addVehicle(bus, "S");
                }
                case "W" -> {
                    route.add("Intersection3:E");
                    route.add("Intersection:E");
                    Vehicle bus = new Bus("Bus " + i, route);
                    vehicles.add(bus);
                    intersection3.addVehicle(bus, "W");
                }
                case "E" -> {
                    route.add("Intersection:W");
                    Vehicle bus = new Bus("Bus " + i, route);
                    vehicles.add(bus);
                    intersection.addVehicle(bus, "E");
                }
            }
        }

        for (int i = 1; i <= 3; i++) {
            List<String> route = new ArrayList<>();
            String entry = entryDirections[(i - 1) % 3];
            // String exit = directionMap.get(entry);
            switch (entry) {
                case "N" -> {
                    route.add("Intersection:S");
                    route.add("Intersection2:S");
                    Vehicle amb = new EmergencyVehicle("Amb " + i, route);
                    vehicles.add(amb);
                    intersection.addVehicle(amb, "N");
                }
                case "S" -> {
                    route.add("Intersection2:N");
                    route.add("Intersection:N");
                    Vehicle amb = new EmergencyVehicle("Amb " + i, route);
                    vehicles.add(amb);
                    intersection2.addVehicle(amb, "S");
                }
                case "W" -> {
                    route.add("Intersection3:E");
                    route.add("Intersection:E");
                    Vehicle amb = new EmergencyVehicle("Amb " + i, route);
                    vehicles.add(amb);
                    intersection3.addVehicle(amb, "W");
                }
                case "E" -> {
                    route.add("Intersection:W");
                    Vehicle amb = new EmergencyVehicle("Amb " + i, route);
                    vehicles.add(amb);
                    intersection.addVehicle(amb, "E");
                }
            }
        }

        List<Intersection> intersections = List.of(intersection, intersection2, intersection3);

        Map<String, TrafficLight> lights = new HashMap<>();
        Map<String, TrafficLight> lights2 = new HashMap<>();
        Map<String, TrafficLight> lights3 = new HashMap<>();

        for (String dir : directionMap.values()) {
            lights.put(dir, new TrafficLight("Intersection:" + dir, Colours.CZERWONY, Colours.ZOLTY));
            lights2.put(dir, new TrafficLight("Intersection2:" + dir, Colours.CZERWONY, Colours.ZOLTY));
            lights3.put(dir, new TrafficLight("Intersection3:" + dir, Colours.CZERWONY, Colours.ZOLTY));
        }

        System.out.println("\n---> START <---");
        System.out.println("Vehicles ammount: " + vehicles.size());
        // System.out.println("Avg wait time (Car): " +
        // TrafficAnalysis.averageWaitTime(vehicles, Car.class));
        // System.out.println("Avg wait time (Bus): " +
        // TrafficAnalysis.averageWaitTime(vehicles, Bus.class));
        // System.out.println("Avg wait time (Emergency): " +
        // TrafficAnalysis.averageWaitTime(vehicles, EmergencyVehicle.class));
        System.out.println("Intersection statuses " + TrafficAnalysis.intersectionStatuses(intersections));
        System.out.println("Vehicles ammount by type and direction: " +
                TrafficAnalysis.vehicleCountByPriorityAndDirection(intersections));
        // System.out.println("Total wait time on the intersection: " +
        // TrafficAnalysis.totalWaitTimeByIntersection(vehicles));

        simulateIntersection(intersection, lights, directionMap, intersection2);
        simulateIntersection(intersection2, lights2, directionMap, null);
        simulateIntersection(intersection3, lights3, directionMap, intersection);

        System.out.println("\n---> END <---");
        System.out.println("Vehicles ammount: " + vehicles.size());
        System.out.println("Avg wait time(Car): " +
                TrafficAnalysis.averageWaitTime(vehicles, Car.class));
        System.out.println("Avg wait time (Bus): " +
                TrafficAnalysis.averageWaitTime(vehicles, Bus.class));
        System.out.println("Avg wait time (Emergency): " +
                TrafficAnalysis.averageWaitTime(vehicles, EmergencyVehicle.class));
        System.out.println("Intersection statuses: " + TrafficAnalysis.intersectionStatuses(intersections));
        System.out.println("Vehicles ammount by type and direction: " +
                TrafficAnalysis.vehicleCountByPriorityAndDirection(intersections));
        System.out.println("Total wait time on the intersection: " +
                TrafficAnalysis.totalWaitTimeByIntersection(vehicles));

    }

    private static void simulateIntersection(Intersection intersection, Map<String, TrafficLight> lights,
            Map<String, String> directionMap, Intersection next) {
        int time = 0;
        while (!intersection.getVehicles().isEmpty()) {
            System.out.println("\n---> Time: " + time + " | " + intersection.getId() + " <---");

            for (Vehicle v : intersection.getVehicles()) {
                v.updateStatus();
            }

            String greenDir = TrafficOptimizer.optimizeTrafficLight(intersection, lights);
            int greenTime = TrafficOptimizer.calculateGreenTime(greenDir, intersection);
            System.out.println("Green light for: " + greenDir + " (" + greenTime + "s)");

            for (int i = 0; i < greenTime; i++) {
                List<Vehicle> passed = new ArrayList<>();

                for (Vehicle v : intersection.getVehicles()) {
                    if (v.getRoute().isEmpty())
                        continue;

                    String dest = v.getRoute().get(0);
                    String expected = intersection.getId() + ":" + greenDir;
                    if (dest.equals(expected)) {
                        v.getRoute().remove(0);

                        String entryDir = getEntryDirection(v, directionMap);
                        intersection.getDirectionQueue().put(
                                entryDir,
                                intersection.getDirectionQueue().get(entryDir) - 1);

                        if (v.getRoute().isEmpty() || next == null) {
                            passed.add(v);
                        } else {
                            next.addVehicle(v, entryDir);
                            passed.add(v);
                        }
                    }
                }

                intersection.getVehicles().removeAll(passed);
                if (intersection.getVehicles().isEmpty())
                    break;
                time++;
            }

            intersection.updateStatus();

            System.out.println("Vehicles left: " + intersection.getVehicles().size());
            System.out.println("Intersection status: " + intersection.getStatus());

            time++;
        }

        intersection.getDirectionQueue().replaceAll((k, v) -> 0);
        intersection.updateStatus();
    }

    private static String getEntryDirection(Vehicle v, Map<String, String> map) {
        if (v.getRoute().isEmpty())
            return "S";

        String exit = v.getRoute().get(0).split(":")[1];
        return map.entrySet().stream()
                .filter(e -> e.getValue().equals(exit))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("S");
    }
}
