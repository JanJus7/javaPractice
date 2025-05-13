package lab4;

import java.time.LocalDateTime;
import java.util.*;

public class Simulator {
    private final Dispatcher dispatcher;
    private final int totalTurns;
    private final Random random = new Random();
    private int currentTurn = 0;

    public Simulator(Dispatcher dispatcher, int totalTurns) {
        this.dispatcher = dispatcher;
        this.totalTurns = totalTurns;
    }

    public void run() {
        System.out.println("=== Simulation begins: (" + totalTurns + " turns) ===\n");
        for (currentTurn = 1; currentTurn <= totalTurns; currentTurn++) {
            System.out.println("Turn #" + currentTurn);

            int numOfNewAccidents = random.nextInt(3);
            for (int i = 0; i < numOfNewAccidents; i++) {
                Accident newAcc = generateRandomAccident();
                dispatcher.addAccident(newAcc);
            }

            dispatcher.update();

            dispatcher.printStatus();
        }

        System.out.println("=== Simulation ends ===");
    }

    private Accident generateRandomAccident() {
        Accident.AccidentType[] types = Accident.AccidentType.values();
        Accident.AccidentType randomType = types[random.nextInt(types.length)];

        String randomLocation = "City-" + (random.nextInt(5) + 1);
        int peopleInjured = random.nextInt(5);

        return new Accident(LocalDateTime.now(), randomLocation, randomType, peopleInjured);
    }
}
