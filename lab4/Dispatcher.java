package lab4;

import java.util.*;

public class Dispatcher {
    private final List<Accident> activeAccidents = new ArrayList<>();
    private final List<Accident> historicalAccidents = new ArrayList<>();
    private final List<Report> activeReports = new ArrayList<>();
    private final List<Report> completedReports = new ArrayList<>();
    private final List<RescueUnit> allUnits;

    public Dispatcher(List<RescueUnit> units) {
        this.allUnits = units;
    }

    public void addAccident(Accident accident) {
        activeAccidents.add(accident);
        System.out.println("New accident reported: " + accident);
        generateReportFromAccident(accident);
    }

    private void generateReportFromAccident(Accident accident) {
        List<Report.RescueUnitType> required = determineUnitsForType(accident.getType());
        int duration = 2 + new Random().nextInt(3);
        Report report = new Report(
            accident.getTimestamp(),
            accident.getLocation(),
            required,
            duration,
            accident.getPeopleInjured()
        );
        activeReports.add(report);
        System.out.println("New report was made: " + report);
        assignUnitsToReport(report);
    }

    private List<Report.RescueUnitType> determineUnitsForType(Accident.AccidentType type) {
        return switch (type) {
            case COLLISION -> List.of(Report.RescueUnitType.POLICE, Report.RescueUnitType.AMBULANCE);
            case FIRE -> List.of(Report.RescueUnitType.FIRE, Report.RescueUnitType.AMBULANCE);
            case EXPLOSION -> List.of(Report.RescueUnitType.FIRE, Report.RescueUnitType.AMBULANCE, Report.RescueUnitType.POLICE);
            case FLOOD -> List.of(Report.RescueUnitType.FIRE, Report.RescueUnitType.POLICE);
            case EARTHQUAKE -> List.of(Report.RescueUnitType.FIRE, Report.RescueUnitType.AMBULANCE, Report.RescueUnitType.POLICE);
            default -> List.of(Report.RescueUnitType.POLICE);
        };
    }

    private void assignUnitsToReport(Report report) {
        for (Report.RescueUnitType type : report.getRequiredUnits()) {
            Optional<RescueUnit> unit = findNearestAvailableUnit(type, report.getLocation());
            unit.ifPresent(u -> {
                u.dispatchTo(report.getLocation());
                report.assignUnit(u);
                System.out.println(u + " unit was assigned to the incident #" + report.getId());
            });
        }
    }

    private Optional<RescueUnit> findNearestAvailableUnit(Report.RescueUnitType type, String location) {
        return allUnits.stream()
            .filter(u -> u.getType().toString().equals(type.toString()) && u.getStatus() == RescueUnit.Status.AVAILABLE)
            .sorted(Comparator.comparingInt(u -> distance(u.getCurrentLocation(), location)))
            .findFirst();
    }

    private int distance(String a, String b) {
        return a.equals(b) ? 0 : 1;
    }

    public void update() {
        Iterator<Report> iterator = activeReports.iterator();
        while (iterator.hasNext()) {
            Report report = iterator.next();
            report.update();

            if (report.getStatus() == Report.Status.INACTIVE) {
                System.out.println("Report #" + report.getId() + " has been completed.");
                completedReports.add(report);
                iterator.remove();
                moveAccidentToHistory(report);
                for (RescueUnit unit : report.getAssignedUnits()) {
                    unit.returnToBase();
                }
            }
        }
    }

    private void moveAccidentToHistory(Report report) {
        Optional<Accident> acc = activeAccidents.stream()
            .filter(a -> a.getLocation().equals(report.getLocation()) && a.isActive())
            .findFirst();

        acc.ifPresent(a -> {
            a.setInactive();
            activeAccidents.remove(a);
            historicalAccidents.add(a);
        });
    }

    public void printHistoricalAccidents() {
        System.out.println("\n=== Accidents History ===");
        for (Accident accident : historicalAccidents) {
            System.out.println(accident);
        }
        System.out.println("==============================\n");
    }

    public void printStatus() {
        System.out.println("\n=== System Status ===");
        System.out.println("Active Reports: " + activeReports.size());
        System.out.println("Completed Reports: " + completedReports.size());
        System.out.println("Units Aviable:");
        allUnits.stream().filter(u -> u.getStatus() == RescueUnit.Status.AVAILABLE).forEach(System.out::println);
        System.out.println("==============================\n");
    }
}
