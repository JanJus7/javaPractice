package lab4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<RescueUnit> units = new ArrayList<>();
        units.add(new PoliceUnit("CityCenter"));
        units.add(new PoliceUnit("Eastern Suburbs"));
        units.add(new FireUnit("West Side"));
        units.add(new FireUnit("Main Station"));
        units.add(new AmbulanceUnit("Hospital 1"));
        units.add(new AmbulanceUnit("Hospital 2"));
        units.add(new AmbulanceUnit("Hospital 3"));


        Dispatcher dispatcher = new Dispatcher(units);

        Simulator simulator = new Simulator(dispatcher, 10);
        simulator.run();
    }
}
