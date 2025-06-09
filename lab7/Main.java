import java.util.*;
import logic.TrafficAnalysis;
import logic.TrafficOptimizer;
import model.*;

public class Main {
    public static void main(String[] args) {
        Intersection intersection = new Intersection("Intersection");
        List<Vehicle> vehicles = new ArrayList<>();

        Map<String, String> directionMap = Map.of(
                "N", "S",
                "S", "N",
                "E", "W",
                "W", "E");
        String[] entryDirections = { "S", "N", "E", "W" };

        for (int i = 1; i <= 87; i++) {
            String entry = entryDirections[(i - 1) % 4];
            String exit = directionMap.get(entry);
            Vehicle car = new Car("Car " + i, new ArrayList<>(List.of("Intersection:" + exit)));
            vehicles.add(car);
            intersection.addVehicle(car, entry);
        }
        for (int i = 1; i <= 10; i++) {
            String entry = entryDirections[(i - 1) % 4];
            String exit = directionMap.get(entry);
            Vehicle bus = new Bus("Bus " + i, new ArrayList<>(List.of("Intersection:" + exit)));
            vehicles.add(bus);
            intersection.addVehicle(bus, entry);
        }
        for (int i = 1; i <= 3; i++) {
            String entry = entryDirections[(i - 1) % 3];
            String exit = directionMap.get(entry);
            Vehicle amb = new EmergencyVehicle("Emergency " + i, new ArrayList<>(List.of("Intersection:" + exit)));
            vehicles.add(amb);
            intersection.addVehicle(amb, entry);
        }

        List<Intersection> intersections = List.of(intersection);

        Map<String, TrafficLight> lights = new HashMap<>();
        for (String dir : directionMap.values()) {
            lights.put(dir, new TrafficLight("Intersection:" + dir, Colours.CZERWONY, Colours.ZOLTY));
        }

        intersection.updateStatus();

        System.out.println("\n---> START <---");
        System.out.println("Vehicles ammount: " + vehicles.size());
        System.out.println("Avg wait time (Car): " +
                TrafficAnalysis.averageWaitTime(vehicles, Car.class));
        System.out.println("Avg wait time (Bus): " +
                TrafficAnalysis.averageWaitTime(vehicles, Bus.class));
        System.out.println("Avg wait time (Emergency): " +
                TrafficAnalysis.averageWaitTime(vehicles, EmergencyVehicle.class));
        System.out.println("Intersection statuses " + TrafficAnalysis.intersectionStatuses(intersections));
        System.out.println("Vehicles ammount by type and direction: " +
                TrafficAnalysis.vehicleCountByPriorityAndDirection(intersections));
        System.out.println("Total wait time on the intersection: " +
                TrafficAnalysis.totalWaitTimeByIntersection(vehicles));

        int time = 0;
        while (!intersection.getVehicles().isEmpty()) {
            System.out.println("\n---> Time: " + time + " <---");

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
                    if (dest.equals("Intersection:" + greenDir)) {
                        v.getRoute().remove(0);

                        String entryDir = getEntryDirection(v, directionMap);
                        intersection.getDirectionQueue().put(
                                entryDir,
                                intersection.getDirectionQueue().get(entryDir) - 1);
                        passed.add(v);
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
