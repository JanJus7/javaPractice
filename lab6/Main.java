package lab6;

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static TaskList taskList = new TaskList(Collections.emptyList());
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1) Add task 2) Show tasks 3) Complete task 4) Show stats 5) Modify title 6) Change priority 7) Change priority by tag 0) Exit");
            switch (scanner.nextLine()) {
                case "1" -> addTask();
                case "2" -> showTasks();
                case "3" -> completeTask();
                case "4" -> showStats();
                case "5" -> modifyTitle();
                case "6" -> changePriority();
                case "7" -> changePriorityByTag();
                case "0" -> { System.out.println("Bye!"); return; }
                default -> System.out.println("Unknown option...");
            }
        }
    }

    private static void addTask() {
        System.out.print("Title: "); String title = scanner.nextLine();
        System.out.print("Description: "); String desc = scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): "); LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Priority (LOW/MEDIUM/HIGH): "); Priority pr = Priority.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Tags (separated with commas ','): "); 
        Set<String> tags = Set.of(scanner.nextLine().split("\\s*,\\s*"));
        
        Task t = TaskFactory.create(title, desc, date, pr, tags);
        taskList = taskList.add(t);
        System.out.println("Added task: " + t.id());
    }

    private static void showTasks() {
        taskList.tasks().forEach(t -> 
            System.out.printf("%s | %s | %s | %s | %s | %b | %s%n",
                t.id(), t.title(), t.description(), t.dueDate(),
                t.priority(), t.completed(), t.tags())
        );
    }

    private static void completeTask() {
        System.out.print("ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        taskList = taskList.modify(id, Utils::markCompleted);
        System.out.println("Task is marked as finished!");
    }

    private static void showStats() {
        System.out.println("Finished: " + taskList.countCompleted());
        taskList.countByPriority().forEach((p, cnt) ->
            System.out.println(p + ": " + cnt)
        );
    }

    private static void modifyTitle() {
        System.out.print("ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        System.out.print("New title: ");
        String newTitle = scanner.nextLine();
        
        taskList = taskList.modify(id, t -> Utils.withTitle(t, newTitle));
        System.out.println("Title changed.");
    }

    private static void changePriority() {
        System.out.print("ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        System.out.print("New priority (LOW/MEDIUM/HIGH): ");
        Priority newPriority = Priority.valueOf(scanner.nextLine().toUpperCase());
        
        taskList = taskList.modify(id, t -> Utils.changePriority(t, newPriority));
        System.out.println("Priority changed.");
    }

    private static void changePriorityByTag() {
        System.out.print("Tag: ");
        String tag = scanner.nextLine();
        System.out.print("New priority (LOW/MEDIUM/HIGH): ");
        Priority newPriority = Priority.valueOf(scanner.nextLine().toUpperCase());
        
        taskList = taskList.mapByTag(tag, t -> Utils.changePriority(t, newPriority));
        System.out.println("Priority with a tag '" + tag + "' has been changed.");
    }
}
